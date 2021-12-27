package ru.kotofeya.storage.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kotofeya.features.M11Creator;
import ru.kotofeya.storage.model.Item;
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

        URL resource = getClass().getClassLoader().getResource("M11.xlsx");
        if (resource == null) {
            System.out.println("file: " + resource + " not found");
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                File source = new File(resource.toURI());
                M11Creator m11Creator = new M11Creator(new FileInputStream(source));
                m11Creator.createNewIncomeBlank(incomeStrings);

                ByteArrayOutputStream outputStream = m11Creator.getOutputStream();
                ByteArrayInputStream byteArrayInputStream =
                        new ByteArrayInputStream(outputStream.toByteArray());
                PrintWriter out = response.getWriter();
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" +
                        "Income_" + incomeMain.getDate() + ".xlsx" + "\"");
                int i;
                while ((i = byteArrayInputStream.read()) != -1) {
                    out.write(i);
                }
                out.close();
                byteArrayInputStream.close();
                outputStream.close();
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

        URL resource = getClass().getClassLoader().getResource("M11.xlsx");
        if (resource == null) {
            System.out.println("file: " + resource + " not found");
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                File source = new File(resource.toURI());
                M11Creator m11Creator = new M11Creator(new FileInputStream(source));
                m11Creator.createNewExpandBlank(expandStrings);
                ByteArrayOutputStream outputStream = m11Creator.getOutputStream();
                ByteArrayInputStream byteArrayInputStream =
                        new ByteArrayInputStream(outputStream.toByteArray());
                PrintWriter out = response.getWriter();
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" +
                        "Expand_" + expandMain.getDate() + ".xlsx" + "\"");
                int i;
                while ((i = byteArrayInputStream.read()) != -1) {
                    out.write(i);
                }
                out.close();
                byteArrayInputStream.close();
                outputStream.close();
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/expands";
    }
}