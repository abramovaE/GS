package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kotofeya.features.M11Creator;
import ru.kotofeya.storage.model.Item;
import ru.kotofeya.storage.model.incomes.IncomeMain;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.service.ItemService;
import ru.kotofeya.storage.service.incomes.IncomeMainService;
import ru.kotofeya.storage.service.incomes.IncomeStringService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlankController {

    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ItemService itemService;


    /*

    @RequestMapping(value = "/makeXlsx/{reportId}")
    @ResponseBody
    public String makeXlsx(HttpServletResponse response, Model model, @PathVariable("reportId") long reportId) {
        Report report = this.reportService.findByIdReport(reportId);
        User user = report.getUser();
//        String surname = user.getSurname() + user.getName().charAt(0) + user.getPatronymic().charAt(0);
        try {
            URL resource = getClass().getClassLoader().getResource("report.xlsx");
            if (resource == null) {
                System.out.println("file: " + resource + " not found");
                throw new IllegalArgumentException("file not found!");
            } else {
                File source = new File(resource.toURI());
                File reports = new File(
                        Constants.MAIN_PATH + "/reports/" +
                                report.getYear() + "/" + report.getMonth());
                if (!reports.exists()) {
                    reports.mkdirs();
                }
                String fileName = report.getMonth() + "_" +
                        report.getYear() + "_" + user.getLogin() + ".xlsx";
                // TODO: 07.10.2021  remove when deploy
//                String p = "/Users/abramov" + "/" + fileName;
                String p = reports.getAbsolutePath() + "/" + fileName;
                String text = "Отчет отправлен на e-mail";

                if (report != null) {
                    Path path = Paths.get(p);
                    if (Files.exists(path)) {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    File resultReport = new File(p);
                    try {
                        Vspom.copyFile(source, resultReport);
                        ExcelParser excelParser = new ExcelParser();
                        excelParser.fillTheReport(resultReport.getAbsolutePath(),
                                report,
//                        reportService.getPreviousReport(report),
                                null,
                                false,
                                this.reportService.allDaysByReport(report),
//                        this.reportService.allDaysByReport(this.reportService.getPreviousReport(report)),
                                null,
                                this.mainSettingsService.getCurrentMS(), report.getAuto());
                        model.addAttribute("text", text);
                        model.addAttribute("link", (report.getMonth()) + "/" + report.getYear() + "/" + user.getLogin());
                        String link =  reports.getAbsolutePath() + "/" + fileName;
                        PrintWriter out = null;
                        try {
                            out = response.getWriter();
                            response.setContentType("APPLICATION/OCTET-STREAM");
                            response.setHeader("Content-Disposition", "attachment; filename=\"" + link + "\"");
                            FileInputStream fileInputStream = new FileInputStream(link);
                            int i;
                            while ((i = fileInputStream.read()) != -1) {
                                out.write(i);
                            }
                            fileInputStream.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return "redirect:/reportsManaging";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/reportsManaging";
    }
}
     */

    @GetMapping("show_income_main/{incomeId}/create_income_blank")
    @ResponseBody
    public String  createIncomeBlank(HttpServletResponse response,
                                     Model model,
                                     @PathVariable("incomeId") Long incomeId) {
        IncomeMain incomeMain = incomeMainService.findById(incomeId);
        List<IncomeString> incomeStrings = incomeStringService.findByIncomeMain(incomeMain);
        System.out.println("income strings size: " + incomeStrings.size());
//        List<Item> incomeItems = new ArrayList<>();
//        incomeStrings.stream().forEach(it->incomeItems.add(itemService.getById(it.getId())));

        URL resource = getClass().getClassLoader().getResource("M11.xlsx");
        System.out.println("res: " + resource.toString());
        if (resource == null) {
            System.out.println("file: " + resource + " not found");
            throw new IllegalArgumentException("file not found!");
        } else {
            File source = null;
            try {
                String p = "/home/kotofeya/web/" + LocalDateTime.now().toString()+ ".xlsx";
                File resultReport = new File(p);
                source = new File(resource.toURI());
                System.out.println("source: " + source.length());
                copyFile(source, resultReport);
                System.out.println("result: " + resultReport.length());
                M11Creator m11Creator = new M11Creator(resultReport, incomeStrings);

                PrintWriter out = null;
                try {
                    String link =  p;
                    out = response.getWriter();
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + link + "\"");
                    FileInputStream fileInputStream = new FileInputStream(link);
                    int i;
                    while ((i = fileInputStream.read()) != -1) {
                        out.write(i);
                    }
                    fileInputStream.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/incomes";
    }

    public static void copyFile(File source, File dest) throws IOException {
        if (Files.exists(dest.toPath())) {
            try {
                Files.delete(dest.toPath());
                String p = "/home/kotofeya/web/noname.xls";
                dest = new File(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Files.copy(source.toPath(), dest.toPath());
    }
}
