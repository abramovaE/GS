package ru.kotofeya.storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kotofeya.features.M11Creator;
import ru.kotofeya.storage.model.expands.ExpandMain;
import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.incomes.IncomeMain;
import ru.kotofeya.storage.model.incomes.IncomeString;
import ru.kotofeya.storage.service.ItemService;
import ru.kotofeya.storage.service.expands.ExpandMainService;
import ru.kotofeya.storage.service.expands.ExpandStringService;
import ru.kotofeya.storage.service.incomes.IncomeMainService;
import ru.kotofeya.storage.service.incomes.IncomeStringService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Controller
public class BlankController {

    @Autowired
    private IncomeMainService incomeMainService;
    @Autowired
    private ExpandMainService expandMainService;
    @Autowired
    private IncomeStringService incomeStringService;
    @Autowired
    private ExpandStringService expandStringService;

    @Autowired
    private ItemService itemService;

    @GetMapping("show_income_main/{incomeId}/create_income_blank")
    @ResponseBody
    public String  createIncomeBlank(HttpServletResponse response,
                                     Model model,
                                     @PathVariable("incomeId") Long incomeId) {
        IncomeMain incomeMain = incomeMainService.findById(incomeId);
        List<IncomeString> incomeStrings = incomeStringService.findByIncomeMain(incomeMain);
        URL resource = getBlankURL();
        String fileName = "Income_" + incomeMain.getDate() + ".xlsx";
        if(resource != null) {
            try {
                M11Creator m11Creator = createM11(resource);
                m11Creator.createNewIncomeBlank(incomeStrings);
                sendDataToResponse(m11Creator, response, fileName);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/incomes";
    }

    @GetMapping("show_expand_main/{expandId}/create_expand_blank")
    @ResponseBody
    public String  createExpandBlank(HttpServletResponse response,
                                     Model model,
                                     @PathVariable("expandId") Long expandId) {
        ExpandMain expandMain = expandMainService.findById(expandId);
        List<ExpandString> expandStrings = expandStringService.findByExpandMain(expandMain);
        URL resource = getBlankURL();
        String fileName = "Expand_" + expandMain.getDate() + ".xlsx";
        if(resource != null) {
            try {
                M11Creator m11Creator = createM11(resource);
                m11Creator.createNewExpandBlank(expandStrings);
                sendDataToResponse(m11Creator, response, fileName);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/expands";
    }

    private M11Creator createM11(URL resource) throws URISyntaxException, FileNotFoundException {
        File source = new File(resource.toURI());
        return new M11Creator(new FileInputStream(source));
    }

    private URL getBlankURL(){
        URL resource = getClass().getClassLoader().getResource("M11.xlsx");
        if (resource == null) {
            System.out.println("file: " + resource + " not found");
            throw new IllegalArgumentException("file not found!");
        }
        return resource;
    }

    private void writeBaisToResponse(ByteArrayInputStream byteArrayInputStream,
                                     HttpServletResponse response,
                                     String resultFileName) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" +
                resultFileName + "\"");
        int i;
        while ((i = byteArrayInputStream.read()) != -1) {
            out.write(i);
        }
        out.close();
    }

    private ByteArrayInputStream copyBaosToBais(M11Creator m11Creator) throws IOException {
        ByteArrayOutputStream outputStream = m11Creator.getOutputStream();
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(outputStream.toByteArray());
        outputStream.close();
        return byteArrayInputStream;
    }

    private void sendDataToResponse(M11Creator m11Creator, HttpServletResponse response,
                                    String fileName) throws IOException {
        ByteArrayInputStream byteArrayInputStream = copyBaosToBais(m11Creator);
        writeBaisToResponse(byteArrayInputStream, response, fileName);
        byteArrayInputStream.close();
    }
}