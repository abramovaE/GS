package ru.kotofeya.features;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class M11Creator {
    private FileInputStream inputStream;
    private Workbook workBook;
    private Sheet sheet0, sheet1;
    private Cell blankNumber, organization, blankCreateDate, operationCode, senderStructOrg,
    senderActType, recStructOrg, recActType, corrAccSub, corrAccCode, accUnitOutput,
    throughWhom, requested, allowed,
            item0_1, item0_2, item0_3, item0_4, item0_5,
            item0_6, item0_7, item0_8, item0_9, item0_10, item0_11,
            item1_1, item1_2, item1_3, item1_4, item1_5,
            item1_6, item1_7, item1_8, item1_9, item1_10, item1_11,
    releasedPost, releasedName, receivedPost, receivedName;

    public M11Creator(File file){
        createNewForm(file);
    }

    private void createNewForm(File file){
        try {
            createNewWorkbook(file);
            initCells();


            OutputStream outputStream = new FileOutputStream(file);
            workBook.write(outputStream);

            workBook.close();
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewWorkbook(File file) throws IOException {
        inputStream = new FileInputStream(file.getAbsolutePath());
        this.workBook = new XSSFWorkbook(inputStream);
    }

    private void initCells(){
        sheet0 = workBook.getSheetAt(0);
        sheet1 = workBook.getSheetAt(1);

        blankNumber = sheet0.getRow(4).getCell(6);
        organization = sheet0.getRow(6).getCell(2);
        blankCreateDate = sheet0.getRow(10).getCell(1);
        operationCode = sheet0.getRow(10).getCell(2);
        senderStructOrg = sheet0.getRow(10).getCell(3);
        senderActType = sheet0.getRow(10).getCell(5);
        recStructOrg = sheet0.getRow(10).getCell(6);
        recActType = sheet0.getRow(10).getCell(7);
        corrAccSub = sheet0.getRow(10).getCell(8);
        corrAccCode = sheet0.getRow(10).getCell(9);
        accUnitOutput = sheet0.getRow(10).getCell(10);
        throughWhom = sheet0.getRow(11).getCell(2);
        requested = sheet0.getRow(12).getCell(2);
        allowed = sheet0.getRow(12).getCell(6);
        item0_1 = sheet0.getRow(17).getCell(0);
        item0_2 = sheet0.getRow(17).getCell(1);
        item0_3 = sheet0.getRow(17).getCell(2);
        item0_4 = sheet0.getRow(17).getCell(3);
        item0_5 = sheet0.getRow(17).getCell(4);
        item0_6 = sheet0.getRow(17).getCell(5);
        item0_7 = sheet0.getRow(17).getCell(6);
        item0_8 = sheet0.getRow(17).getCell(7);
        item0_9 = sheet0.getRow(17).getCell(8);
        item0_10 = sheet0.getRow(17).getCell(9);
        item0_11 = sheet0.getRow(17).getCell(10);

        item1_1 = sheet1.getRow(5).getCell(0);
        item1_2 = sheet1.getRow(5).getCell(1);
        item1_3 = sheet1.getRow(5).getCell(2);
        item1_4 = sheet1.getRow(5).getCell(3);
        item1_5 = sheet1.getRow(5).getCell(4);
        item1_6 = sheet1.getRow(5).getCell(5);
        item1_7 = sheet1.getRow(5).getCell(6);
        item1_8 = sheet1.getRow(5).getCell(7);
        item1_9 = sheet1.getRow(5).getCell(8);
        item1_10 = sheet1.getRow(5).getCell(9);
        item1_11 = sheet1.getRow(5).getCell(10);


        releasedPost = sheet1.getRow(14).getCell(2);
        releasedName = sheet1.getRow(14).getCell(8);
        receivedPost = sheet1.getRow(16).getCell(2);
        receivedName = sheet1.getRow(16).getCell(8);

    }

    private void exportNewForm(String fileName){
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            workBook.write(fileOutputStream);
            workBook.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound exception");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }


}


/*
private Workbook workBook;
  private Report report;
  private User user;
  private Auto userAuto;
  private Sheet sheet;

  private Cell driver, post, month,
          currentNorm, winterNorm, summerNorm,
          auto, autoNumber, summDistance, poluchenoToplivaTotal,
          summSumm, rashodFactSumm, rashodNormSumm, rashodPoNormeSumm,
          countOfWorkingDays, underDriver, buh , buhValue;

  private XSSFCellStyle yellowDateStyle;

  public void fillTheReport(String filename, Report report, Report prevReport,
                            boolean isDirector, List<Day> days, List<Day> prevReportDays,
                            MainSettings mainSettings, Auto userAuto){
    InputStream inputStream = null;
    OutputStream outputStream = null;
    User user = report.getUser();
    this.report = report;
    this.user = user;
    this.userAuto = userAuto;
    try {
      inputStream = new FileInputStream(filename);
      workBook = new XSSFWorkbook(inputStream);
      outputStream = new FileOutputStream(filename);


      sheet = workBook.getSheetAt(0);
      YearMonth yearMonth = YearMonth.of(report.getYear(), report.getMonth());
      workBook.setSheetName(0, DateVspom.getPeriodFromYearMonth(yearMonth));
//заливка желтым для даты
      XSSFCell cellDate = (XSSFCell) sheet.getRow(13).getCell(1);
      yellowDateStyle = (XSSFCellStyle) cellDate.getCellStyle().clone();
      yellowDateStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
      yellowDateStyle.setFillForegroundColor(YELLOW);
      setCell();

      String surnameValue = user.getSurname();
      String nameValue = user.getName();
      String patronymicValue = user.getPatronymic();
      String postValue = user.getPost();

      String surname = surnameValue != null ? surnameValue : "";
      String name = nameValue != null ? nameValue : "";
      String patronymic = patronymicValue != null ? patronymicValue : "";
      String p = postValue != null ? postValue : "";

//    "Сведения о водителе: " +
      driver.setCellValue(surname + " " + name + " " + patronymic + " " + post);
      post.setCellValue(p);
      month.setCellValue(DateVspom.getPeriodFromYearMonth(yearMonth));

//    Выбор текущей нормы насхода бензина
      int monthNumber = report.getMonth();
      Double winterNormValue = userAuto.getWinterNorm();
      Double summerNormValue = userAuto.getSummerNorm();
      String brandValue = userAuto.getBrand();
      String autoNumberValue = userAuto.getNumber();

      if(monthNumber > 3 && monthNumber < 11) {
        currentNorm.setCellValue(summerNormValue != null ? summerNormValue : 0d);
      } else {
        currentNorm.setCellValue(winterNormValue != null ? winterNormValue : 0d);
      }
      winterNorm.setCellValue(winterNormValue != null ? winterNormValue : 0d);
      summerNorm.setCellValue(summerNormValue != null ? summerNormValue : 0d);
      auto.setCellValue(brandValue != null ? brandValue : "");
      autoNumber.setCellValue(autoNumberValue != null ? autoNumberValue : "");

      int sD = report.getSumKmDistance();
      summDistance.setCellValue(sD);
//            УЗНАТЬ КАК СЧИТАЕТСЯ
      countOfWorkingDays.setCellValue(days.size());
      Double sumSummValue = report.getSumSumm();
      summSumm.setCellValue(sumSummValue != null ? sumSummValue : 0d);

      String subName = "";
      String subPatronymic = "";
      if(name.length() > 1){
        subName = name.substring(0,1);
      }
      if (patronymic.length() > 1 ){
        subPatronymic = patronymic.substring(0,1);
      }
      underDriver.setCellValue(surname + " " + subName + "." + subPatronymic + ".");
      int startRow = 13;
      double pTT = 0;
      int countsOfDaysInMonth = yearMonth.lengthOfMonth();

      List<Integer> daysNumbers = days.stream()
              .map(it->Integer.parseInt(it.getDate().split("\\.")[0]))
              .collect(Collectors.toList());
      String[] dayMonthYear = days.get(0).getDate().split("\\.");

      for(int i = 1; i < countsOfDaysInMonth + 1; i++){
        if(!daysNumbers.contains(i)) {
          Day day = new Day();
          String date = i + "." + dayMonthYear[1] + "." + dayMonthYear[2];
          day.setDate( i > 9 ? date : "0" + date);
          days.add(day);
       }
     }

      Collections.sort(days, new Comparator<Day>() {
        @Override
        public int compare(Day o1, Day o2) {
          return o1.getDate().compareTo(o2.getDate());}
      });

      System.out.println("days: " + days);

      for(int j = 0; j < days.size(); j++){
        Day day = days.get(j);
        RouteListForDay routeListForDay = new RouteListForDay(day);
        LocalDate localDate = LocalDate.of(report.getYear(), report.getMonth(),
                Integer.parseInt(day.getDate().split("\\.")[0]));

//                    дата
        Cell date = sheet.getRow(startRow).getCell(1);
//                   показания спидометра на начало и конец рабочего дня
        Cell startOfWorkingDay = sheet.getRow(startRow).getCell(2);
        Cell endOfWorkingDay = sheet.getRow(startRow).getCell(3);
//                    пробег за день
        Cell dayDistance = sheet.getRow(startRow).getCell(4);
//                    маршрут движения
        Cell route = sheet.getRow(startRow).getCell(6);
//                    получено топлива
        Cell poluchenoTopliva = sheet.getRow(startRow).getCell(7);
//                    цена топлива по чеку за 1 литр
        Cell costByLiter = sheet.getRow(startRow).getCell(8);
//                    итого по чеку
        Cell summ = sheet.getRow(startRow).getCell(9);
//                    расход фактический
        Cell rashodFact = sheet.getRow(startRow).getCell(11);
//                    расход по норме
        Cell rashodNorm = sheet.getRow(startRow).getCell(12);
//                    расход топлива по норме
        Cell rashodToplivaPoNorme = sheet.getRow(startRow).getCell(14);

        List<Point> points = null;
        if(day.getId() != 0) {
          points = day.getPoints();
        }

        int dayDistRound = fillTheRouteListGetDayDistance(points, routeListForDay);

        date.setCellValue(day.getDate());
        if(day.getDayDistance() == 0) {
          setHolidayCellStyle(localDate, date);
        } else {
          dayDistance.setCellValue(dayDistRound);
          routeListForDay.sumDistance.setCellValue(dayDistRound);
//                        установка показаний одометра для директоров
          if (isDirector) {
            int st = startRow + 1;
            startOfWorkingDay.setCellFormula("(D" + startRow + ")");
            endOfWorkingDay.setCellFormula("(C" + st + "+E" + st + ")");
          }
        }

//установка маршрута с выстой строки в зависимости от длины маршрута
        String r = getRoute(points);
        route.setCellValue(r);
        setRowHeight(route, r.length());

// установка стоимости топлива за литр (если не было заправки на первую поездку, смотрим последнюю цену из предыдущего отчета)
        costByLiter.setCellType(Cell.CELL_TYPE_NUMERIC);
        if(day.getCostByLiter() != null && day.getSumm() != null) {
          costByLiter.setCellValue(day.getCostByLiter());
          summ.setCellValue(day.getSumm());
          double d = day.getSumm()/day.getCostByLiter();
          poluchenoTopliva.setCellValue(d);
          rashodFact.setCellValue(d);
          pTT = pTT + d;
        }
        if(day.getDayDistance() != 0 && day.getSumm() == null){
          int k = j;
          while (k > 0 && days.get(k).getCostByLiter() == null){
            k--;
          }
          if (k > 0 || (k == 0 && days.get(k).getCostByLiter() != null)) {
            costByLiter.setCellValue(days.get(k).getCostByLiter());
          }
          if(k == 0 && days.get(k).getCostByLiter() == null) {
            if(prevReport != null){
              Collections.sort(prevReportDays, new Comparator<Day>() {
                @Override
                public int compare(Day o1, Day o2) {
                  return o2.getDate().compareTo(o2.getDate());
                }
              });
              double lastCostByLiter;
              for(int m = 0; m< prevReportDays.size(); m++){
                if(prevReportDays.get(m).getCostByLiter() != null){
                  lastCostByLiter = prevReportDays.get(m).getCostByLiter();
                  costByLiter.setCellValue(lastCostByLiter);
                  break;
                }
              }
            }
          }
        }
        if(day.getDayDistance() != 0){
          Double dayKmDistance = day.getDayDistance()/1000d;
          double poNorme = new BigDecimal(dayKmDistance * currentNorm.getNumericCellValue()/100).setScale(2, RoundingMode.HALF_UP).doubleValue();
          double rashodPoNorme = new BigDecimal(poNorme*costByLiter.getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();
          rashodNorm.setCellValue(poNorme);
          rashodToplivaPoNorme.setCellValue(rashodPoNorme);
        }
        startRow++;
      }

      rashodFactSumm.setCellFormula("SUM(L14:L44)");
      rashodNormSumm.setCellFormula("SUM(M14:M44)");
      rashodPoNormeSumm.setCellFormula("SUM(O14:O44)");
      buh.setCellValue("Бухгалтер");
      buhValue.setCellValue(mainSettings.getGlavBuh().getShortFullName());
      if(isDirector) {
        XSSFCellStyle style = (XSSFCellStyle) countOfWorkingDays.getCellStyle();
        Set<Cell> forSetStyle = new HashSet<>();
        for(int i=47; i<70; i++){
          sheet.createRow(i);
        }
        Cell pokazania = sheet.getRow(51).createCell(1);
        Cell start = sheet.getRow(51).createCell(3);
        Cell startValue = sheet.getRow(51).getCell(4);
        Cell end = sheet.getRow(52).createCell(3);
        Cell endValue = sheet.getRow(52).createCell(4);
        Cell probeg = sheet.getRow(53).createCell(3);
        Cell probegValue = sheet.getRow(53).createCell(4);
        Cell mediumProbeg = sheet.getRow(54).createCell(3);
        Cell mediumProbegValue = sheet.getRow(54).createCell(4);
        Cell voditel = sheet.getRow(56).createCell(1);
        Cell voditelValue = sheet.getRow(56).createCell(4);
        Cell director = sheet.getRow(58).createCell(1);
        Cell directorValue = sheet.getRow(58).createCell(4);
        buh = sheet.getRow(60).createCell(1);
        buhValue = sheet.getRow(60).createCell(4);
        for(int i = 47; i < 70; i++){
          for(int j = 0; j < 10; j++){
            forSetStyle.add(sheet.getRow(i).getCell(j));
          }
        }
        for(Cell cell: forSetStyle){
          if(cell != null)
            cell.setCellStyle(style);
        }
        pokazania.setCellValue("показания спидометра");
        start.setCellValue("начало мес.");
        end.setCellValue("конец мес.");
        probeg.setCellValue("пробег");
        probegValue.setCellValue(summDistance.getNumericCellValue());
        mediumProbeg.setCellValue("средний пробег");
        mediumProbegValue.setCellValue(probegValue.getNumericCellValue()/countOfWorkingDays.getNumericCellValue());
        voditel.setCellValue("Водитель");
        voditelValue.setCellValue(user.getShortFullName());
        director.setCellValue("Генеральный директор");
        directorValue.setCellValue(mainSettings.getGenDir().getShortFullName());
        buh.setCellValue("Бухгалтер");
        buhValue.setCellValue(mainSettings.getGlavBuh().getShortFullName());
        sheet.setColumnHidden(8, true);
        sheet.setColumnHidden(9, true);
        sheet.setColumnHidden(14, true);
      }
      poluchenoToplivaTotal.setCellValue(pTT);
      workBook.write(outputStream);
      workBook.close();
      inputStream.close();
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

 */