package no.ias.app.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static String getBaseHtmlTemplateWithStylePartTwo() {

        return """
                 
                  border-radius: 50%;
                    width: 150px;
                    height: 150px;
                }
                    body {
                      background-color: silver;
                      display: flex;
                      justify-content: center;
                      align-items: center;
                      flex-direction: column;
                    }
                    
                    #my-pie-chart-container {
                      display: flex;
                      align-items: center;
                    }
            
                    #legenda {
                      margin-left: 20px;
                      background-color: white;
                      padding: 5px;
                    }
                    
                    .entry {
                      display: flex;
                      align-items: center;
                    }
                    
                    .entry-color {
                        height: 10px;
                        width: 10px;
                    }
                    
                    .entry-text {
                      margin-left: 5px;
                    }
                    
                    #color-red {
                      background-color: red;
                    }
                    
                    #color-orange {
                      background-color: orange;
                    }
                    
                    #color-yellow {
                      background-color: yellow;
                    }
                    
                    #color-green {
                      background-color: green;
                    }
                    
                    #color-blue {
                      background-color: blue;
                    }
                    
                    #color-black {
                      background-color: black;
                    }
                    
                    #color-brown {
                      background-color: brown;
                    }
                    </style>
                    </head>
                    <body>
                    <h1>Feedback Status</h1>
                    
                    <div id="my-pie-chart-container">
                      <div id="my-pie-chart"></div>
                    
                      <div id="legenda">
                        <div class="entry">
                          <div id="color-brown" class="entry-color"></div>
                          <div class="entry-text">Zero Stars</div>
                        </div>
                        <div class="entry">
                          <div id="color-black" class="entry-color"></div>
                          <div class="entry-text">One Star</div>
                        </div>
                        <div class="entry">
                          <div id="color-blue" class="entry-color"></div>
                          <div class="entry-text">Two Stars</div>
                        </div>
                        <div class="entry">
                          <div id="color-green" class="entry-color"></div>
                          <div class="entry-text">Three Stars</div>
                        </div>
                        <div class="entry">
                          <div id="color-yellow" class="entry-color"></div>
                          <div class="entry-text">Four Stars</div>
                        </div>
                        <div class="entry">
                          <div id="color-orange" class="entry-color"></div>
                          <div class="entry-text">Five Stars</div>
                        </div>
                      </div>
                    </div>
                    </body>
                    </html>
                """;
    }

    public static String getBaseHtmlTemplateWithStylePartOne() {

        return """
               <html>
                <head>
                <style type="text/css">
                #my-pie-chart {
                  background: conic-gradient""";
    }

    public static String updatedHtmlTemplate(Map<Integer, Double> percentageMap) {


        Map<String, Double> colourMap = new HashMap<>();

        for (Map.Entry<Integer, Double> entry : percentageMap.entrySet()) {
            switch (entry.getKey()) {
                case 0 -> colourMap.put("brown", entry.getValue());
                case 1 -> colourMap.put("black", percentageMap.get(0) + entry.getValue());
                case 2 -> colourMap.put("blue", percentageMap.get(0) + percentageMap.get(1) + entry.getValue());
                case 3 -> colourMap.put("green", percentageMap.get(0) + percentageMap.get(1) + entry.getValue() + percentageMap.get(2));
                case 4 -> colourMap.put("yellow", percentageMap.get(0) + percentageMap.get(1) + entry.getValue() + percentageMap.get(2) + percentageMap.get(3));
                case 5 -> colourMap.put("orange", percentageMap.get(5));
                default -> colourMap.put("default", 0.0);

            }
        }

        String formattedString = "brown %s, black %s %s, blue %s %s, green %s %s, yellow %s %s, orange %s".formatted(
                colourMap.get("brown")+"%",
                colourMap.get("brown")+"%",
                colourMap.get("black")+"%",
                colourMap.get("black")+"%",
                colourMap.get("blue")+"%",
                colourMap.get("blue")+"%",
                colourMap.get("green")+"%",
                colourMap.get("green")+"%",
                colourMap.get("yellow")+"%",
                colourMap.get("yellow")+"%");
        var s = getBaseHtmlTemplateWithStylePartOne().concat("("+formattedString+");");
        return s.concat(getBaseHtmlTemplateWithStylePartTwo());
    }
}
