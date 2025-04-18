/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 4.0, "minX": 0.0, "maxY": 533.0, "series": [{"data": [[0.0, 4.0], [0.1, 4.0], [0.2, 4.0], [0.3, 4.0], [0.4, 4.0], [0.5, 4.0], [0.6, 4.0], [0.7, 4.0], [0.8, 5.0], [0.9, 5.0], [1.0, 5.0], [1.1, 5.0], [1.2, 5.0], [1.3, 5.0], [1.4, 5.0], [1.5, 5.0], [1.6, 5.0], [1.7, 5.0], [1.8, 5.0], [1.9, 5.0], [2.0, 5.0], [2.1, 5.0], [2.2, 5.0], [2.3, 5.0], [2.4, 5.0], [2.5, 5.0], [2.6, 5.0], [2.7, 5.0], [2.8, 5.0], [2.9, 5.0], [3.0, 5.0], [3.1, 5.0], [3.2, 5.0], [3.3, 5.0], [3.4, 5.0], [3.5, 5.0], [3.6, 5.0], [3.7, 5.0], [3.8, 5.0], [3.9, 6.0], [4.0, 6.0], [4.1, 6.0], [4.2, 6.0], [4.3, 6.0], [4.4, 6.0], [4.5, 6.0], [4.6, 6.0], [4.7, 6.0], [4.8, 6.0], [4.9, 6.0], [5.0, 6.0], [5.1, 6.0], [5.2, 6.0], [5.3, 6.0], [5.4, 6.0], [5.5, 6.0], [5.6, 6.0], [5.7, 6.0], [5.8, 6.0], [5.9, 6.0], [6.0, 6.0], [6.1, 6.0], [6.2, 6.0], [6.3, 6.0], [6.4, 6.0], [6.5, 6.0], [6.6, 6.0], [6.7, 6.0], [6.8, 6.0], [6.9, 6.0], [7.0, 6.0], [7.1, 6.0], [7.2, 6.0], [7.3, 6.0], [7.4, 6.0], [7.5, 6.0], [7.6, 6.0], [7.7, 6.0], [7.8, 6.0], [7.9, 7.0], [8.0, 7.0], [8.1, 7.0], [8.2, 7.0], [8.3, 7.0], [8.4, 7.0], [8.5, 7.0], [8.6, 7.0], [8.7, 7.0], [8.8, 7.0], [8.9, 7.0], [9.0, 7.0], [9.1, 7.0], [9.2, 7.0], [9.3, 7.0], [9.4, 7.0], [9.5, 7.0], [9.6, 7.0], [9.7, 7.0], [9.8, 7.0], [9.9, 7.0], [10.0, 7.0], [10.1, 7.0], [10.2, 7.0], [10.3, 7.0], [10.4, 7.0], [10.5, 7.0], [10.6, 7.0], [10.7, 7.0], [10.8, 7.0], [10.9, 7.0], [11.0, 7.0], [11.1, 7.0], [11.2, 7.0], [11.3, 7.0], [11.4, 7.0], [11.5, 7.0], [11.6, 7.0], [11.7, 7.0], [11.8, 7.0], [11.9, 8.0], [12.0, 8.0], [12.1, 8.0], [12.2, 8.0], [12.3, 8.0], [12.4, 8.0], [12.5, 8.0], [12.6, 8.0], [12.7, 8.0], [12.8, 8.0], [12.9, 8.0], [13.0, 8.0], [13.1, 8.0], [13.2, 8.0], [13.3, 8.0], [13.4, 8.0], [13.5, 8.0], [13.6, 8.0], [13.7, 8.0], [13.8, 8.0], [13.9, 8.0], [14.0, 8.0], [14.1, 8.0], [14.2, 8.0], [14.3, 8.0], [14.4, 8.0], [14.5, 8.0], [14.6, 8.0], [14.7, 8.0], [14.8, 8.0], [14.9, 8.0], [15.0, 8.0], [15.1, 8.0], [15.2, 8.0], [15.3, 8.0], [15.4, 8.0], [15.5, 8.0], [15.6, 8.0], [15.7, 9.0], [15.8, 9.0], [15.9, 9.0], [16.0, 9.0], [16.1, 9.0], [16.2, 9.0], [16.3, 9.0], [16.4, 9.0], [16.5, 9.0], [16.6, 9.0], [16.7, 9.0], [16.8, 9.0], [16.9, 9.0], [17.0, 9.0], [17.1, 9.0], [17.2, 9.0], [17.3, 9.0], [17.4, 9.0], [17.5, 9.0], [17.6, 9.0], [17.7, 9.0], [17.8, 9.0], [17.9, 9.0], [18.0, 9.0], [18.1, 9.0], [18.2, 9.0], [18.3, 10.0], [18.4, 10.0], [18.5, 10.0], [18.6, 10.0], [18.7, 10.0], [18.8, 10.0], [18.9, 10.0], [19.0, 10.0], [19.1, 10.0], [19.2, 10.0], [19.3, 10.0], [19.4, 10.0], [19.5, 10.0], [19.6, 10.0], [19.7, 10.0], [19.8, 10.0], [19.9, 10.0], [20.0, 10.0], [20.1, 10.0], [20.2, 10.0], [20.3, 10.0], [20.4, 11.0], [20.5, 11.0], [20.6, 11.0], [20.7, 11.0], [20.8, 11.0], [20.9, 11.0], [21.0, 11.0], [21.1, 11.0], [21.2, 11.0], [21.3, 11.0], [21.4, 11.0], [21.5, 11.0], [21.6, 11.0], [21.7, 11.0], [21.8, 11.0], [21.9, 11.0], [22.0, 11.0], [22.1, 11.0], [22.2, 11.0], [22.3, 11.0], [22.4, 12.0], [22.5, 12.0], [22.6, 12.0], [22.7, 12.0], [22.8, 12.0], [22.9, 12.0], [23.0, 12.0], [23.1, 12.0], [23.2, 12.0], [23.3, 12.0], [23.4, 12.0], [23.5, 12.0], [23.6, 12.0], [23.7, 12.0], [23.8, 12.0], [23.9, 12.0], [24.0, 12.0], [24.1, 12.0], [24.2, 13.0], [24.3, 13.0], [24.4, 13.0], [24.5, 13.0], [24.6, 13.0], [24.7, 13.0], [24.8, 13.0], [24.9, 13.0], [25.0, 13.0], [25.1, 13.0], [25.2, 13.0], [25.3, 13.0], [25.4, 14.0], [25.5, 14.0], [25.6, 14.0], [25.7, 14.0], [25.8, 14.0], [25.9, 14.0], [26.0, 14.0], [26.1, 14.0], [26.2, 14.0], [26.3, 14.0], [26.4, 15.0], [26.5, 15.0], [26.6, 15.0], [26.7, 15.0], [26.8, 15.0], [26.9, 15.0], [27.0, 15.0], [27.1, 15.0], [27.2, 15.0], [27.3, 15.0], [27.4, 15.0], [27.5, 16.0], [27.6, 16.0], [27.7, 16.0], [27.8, 16.0], [27.9, 16.0], [28.0, 16.0], [28.1, 16.0], [28.2, 16.0], [28.3, 16.0], [28.4, 16.0], [28.5, 16.0], [28.6, 17.0], [28.7, 17.0], [28.8, 17.0], [28.9, 17.0], [29.0, 17.0], [29.1, 17.0], [29.2, 17.0], [29.3, 17.0], [29.4, 17.0], [29.5, 18.0], [29.6, 18.0], [29.7, 18.0], [29.8, 18.0], [29.9, 18.0], [30.0, 18.0], [30.1, 19.0], [30.2, 19.0], [30.3, 19.0], [30.4, 19.0], [30.5, 19.0], [30.6, 19.0], [30.7, 19.0], [30.8, 19.0], [30.9, 20.0], [31.0, 20.0], [31.1, 20.0], [31.2, 20.0], [31.3, 20.0], [31.4, 20.0], [31.5, 20.0], [31.6, 20.0], [31.7, 21.0], [31.8, 21.0], [31.9, 21.0], [32.0, 21.0], [32.1, 21.0], [32.2, 21.0], [32.3, 21.0], [32.4, 22.0], [32.5, 22.0], [32.6, 22.0], [32.7, 22.0], [32.8, 22.0], [32.9, 22.0], [33.0, 23.0], [33.1, 23.0], [33.2, 23.0], [33.3, 23.0], [33.4, 23.0], [33.5, 23.0], [33.6, 24.0], [33.7, 24.0], [33.8, 24.0], [33.9, 24.0], [34.0, 25.0], [34.1, 25.0], [34.2, 25.0], [34.3, 25.0], [34.4, 25.0], [34.5, 25.0], [34.6, 25.0], [34.7, 26.0], [34.8, 26.0], [34.9, 26.0], [35.0, 26.0], [35.1, 27.0], [35.2, 27.0], [35.3, 27.0], [35.4, 27.0], [35.5, 27.0], [35.6, 27.0], [35.7, 27.0], [35.8, 28.0], [35.9, 28.0], [36.0, 28.0], [36.1, 28.0], [36.2, 29.0], [36.3, 29.0], [36.4, 29.0], [36.5, 29.0], [36.6, 29.0], [36.7, 30.0], [36.8, 30.0], [36.9, 30.0], [37.0, 30.0], [37.1, 30.0], [37.2, 30.0], [37.3, 30.0], [37.4, 30.0], [37.5, 31.0], [37.6, 31.0], [37.7, 31.0], [37.8, 31.0], [37.9, 31.0], [38.0, 31.0], [38.1, 31.0], [38.2, 32.0], [38.3, 32.0], [38.4, 32.0], [38.5, 32.0], [38.6, 32.0], [38.7, 32.0], [38.8, 33.0], [38.9, 33.0], [39.0, 33.0], [39.1, 33.0], [39.2, 33.0], [39.3, 33.0], [39.4, 33.0], [39.5, 34.0], [39.6, 34.0], [39.7, 34.0], [39.8, 34.0], [39.9, 34.0], [40.0, 34.0], [40.1, 34.0], [40.2, 34.0], [40.3, 34.0], [40.4, 35.0], [40.5, 35.0], [40.6, 35.0], [40.7, 35.0], [40.8, 35.0], [40.9, 35.0], [41.0, 35.0], [41.1, 35.0], [41.2, 36.0], [41.3, 36.0], [41.4, 36.0], [41.5, 36.0], [41.6, 36.0], [41.7, 36.0], [41.8, 36.0], [41.9, 36.0], [42.0, 37.0], [42.1, 37.0], [42.2, 37.0], [42.3, 37.0], [42.4, 37.0], [42.5, 37.0], [42.6, 37.0], [42.7, 38.0], [42.8, 38.0], [42.9, 38.0], [43.0, 38.0], [43.1, 38.0], [43.2, 38.0], [43.3, 38.0], [43.4, 38.0], [43.5, 39.0], [43.6, 39.0], [43.7, 39.0], [43.8, 39.0], [43.9, 39.0], [44.0, 39.0], [44.1, 40.0], [44.2, 40.0], [44.3, 40.0], [44.4, 40.0], [44.5, 40.0], [44.6, 40.0], [44.7, 40.0], [44.8, 40.0], [44.9, 40.0], [45.0, 40.0], [45.1, 41.0], [45.2, 41.0], [45.3, 41.0], [45.4, 41.0], [45.5, 41.0], [45.6, 41.0], [45.7, 41.0], [45.8, 41.0], [45.9, 41.0], [46.0, 41.0], [46.1, 41.0], [46.2, 42.0], [46.3, 42.0], [46.4, 42.0], [46.5, 42.0], [46.6, 42.0], [46.7, 42.0], [46.8, 42.0], [46.9, 42.0], [47.0, 42.0], [47.1, 42.0], [47.2, 43.0], [47.3, 43.0], [47.4, 43.0], [47.5, 43.0], [47.6, 43.0], [47.7, 43.0], [47.8, 43.0], [47.9, 43.0], [48.0, 43.0], [48.1, 43.0], [48.2, 43.0], [48.3, 43.0], [48.4, 44.0], [48.5, 44.0], [48.6, 44.0], [48.7, 44.0], [48.8, 44.0], [48.9, 44.0], [49.0, 44.0], [49.1, 44.0], [49.2, 44.0], [49.3, 44.0], [49.4, 44.0], [49.5, 44.0], [49.6, 45.0], [49.7, 45.0], [49.8, 45.0], [49.9, 45.0], [50.0, 45.0], [50.1, 45.0], [50.2, 45.0], [50.3, 45.0], [50.4, 45.0], [50.5, 45.0], [50.6, 45.0], [50.7, 45.0], [50.8, 45.0], [50.9, 46.0], [51.0, 46.0], [51.1, 46.0], [51.2, 46.0], [51.3, 46.0], [51.4, 46.0], [51.5, 46.0], [51.6, 46.0], [51.7, 46.0], [51.8, 46.0], [51.9, 46.0], [52.0, 46.0], [52.1, 46.0], [52.2, 47.0], [52.3, 47.0], [52.4, 47.0], [52.5, 47.0], [52.6, 47.0], [52.7, 47.0], [52.8, 47.0], [52.9, 47.0], [53.0, 47.0], [53.1, 47.0], [53.2, 47.0], [53.3, 47.0], [53.4, 47.0], [53.5, 47.0], [53.6, 48.0], [53.7, 48.0], [53.8, 48.0], [53.9, 48.0], [54.0, 48.0], [54.1, 48.0], [54.2, 48.0], [54.3, 48.0], [54.4, 48.0], [54.5, 48.0], [54.6, 48.0], [54.7, 48.0], [54.8, 48.0], [54.9, 48.0], [55.0, 48.0], [55.1, 48.0], [55.2, 49.0], [55.3, 49.0], [55.4, 49.0], [55.5, 49.0], [55.6, 49.0], [55.7, 49.0], [55.8, 49.0], [55.9, 49.0], [56.0, 49.0], [56.1, 49.0], [56.2, 49.0], [56.3, 49.0], [56.4, 49.0], [56.5, 49.0], [56.6, 49.0], [56.7, 50.0], [56.8, 50.0], [56.9, 50.0], [57.0, 50.0], [57.1, 50.0], [57.2, 50.0], [57.3, 50.0], [57.4, 50.0], [57.5, 50.0], [57.6, 50.0], [57.7, 50.0], [57.8, 50.0], [57.9, 50.0], [58.0, 50.0], [58.1, 50.0], [58.2, 51.0], [58.3, 51.0], [58.4, 51.0], [58.5, 51.0], [58.6, 51.0], [58.7, 51.0], [58.8, 51.0], [58.9, 51.0], [59.0, 51.0], [59.1, 51.0], [59.2, 51.0], [59.3, 51.0], [59.4, 51.0], [59.5, 52.0], [59.6, 52.0], [59.7, 52.0], [59.8, 52.0], [59.9, 52.0], [60.0, 52.0], [60.1, 52.0], [60.2, 52.0], [60.3, 52.0], [60.4, 52.0], [60.5, 52.0], [60.6, 52.0], [60.7, 52.0], [60.8, 52.0], [60.9, 52.0], [61.0, 53.0], [61.1, 53.0], [61.2, 53.0], [61.3, 53.0], [61.4, 53.0], [61.5, 53.0], [61.6, 53.0], [61.7, 53.0], [61.8, 53.0], [61.9, 53.0], [62.0, 53.0], [62.1, 53.0], [62.2, 53.0], [62.3, 53.0], [62.4, 54.0], [62.5, 54.0], [62.6, 54.0], [62.7, 54.0], [62.8, 54.0], [62.9, 54.0], [63.0, 54.0], [63.1, 54.0], [63.2, 54.0], [63.3, 54.0], [63.4, 54.0], [63.5, 54.0], [63.6, 55.0], [63.7, 55.0], [63.8, 55.0], [63.9, 55.0], [64.0, 55.0], [64.1, 55.0], [64.2, 55.0], [64.3, 55.0], [64.4, 55.0], [64.5, 55.0], [64.6, 55.0], [64.7, 56.0], [64.8, 56.0], [64.9, 56.0], [65.0, 56.0], [65.1, 56.0], [65.2, 56.0], [65.3, 56.0], [65.4, 56.0], [65.5, 57.0], [65.6, 57.0], [65.7, 57.0], [65.8, 57.0], [65.9, 57.0], [66.0, 57.0], [66.1, 57.0], [66.2, 57.0], [66.3, 57.0], [66.4, 57.0], [66.5, 58.0], [66.6, 58.0], [66.7, 58.0], [66.8, 58.0], [66.9, 58.0], [67.0, 58.0], [67.1, 58.0], [67.2, 59.0], [67.3, 59.0], [67.4, 59.0], [67.5, 59.0], [67.6, 59.0], [67.7, 59.0], [67.8, 59.0], [67.9, 59.0], [68.0, 59.0], [68.1, 60.0], [68.2, 60.0], [68.3, 60.0], [68.4, 60.0], [68.5, 60.0], [68.6, 60.0], [68.7, 60.0], [68.8, 61.0], [68.9, 61.0], [69.0, 61.0], [69.1, 61.0], [69.2, 61.0], [69.3, 61.0], [69.4, 61.0], [69.5, 61.0], [69.6, 62.0], [69.7, 62.0], [69.8, 62.0], [69.9, 62.0], [70.0, 62.0], [70.1, 63.0], [70.2, 63.0], [70.3, 63.0], [70.4, 64.0], [70.5, 64.0], [70.6, 64.0], [70.7, 64.0], [70.8, 64.0], [70.9, 65.0], [71.0, 65.0], [71.1, 65.0], [71.2, 65.0], [71.3, 65.0], [71.4, 66.0], [71.5, 66.0], [71.6, 66.0], [71.7, 66.0], [71.8, 67.0], [71.9, 67.0], [72.0, 67.0], [72.1, 67.0], [72.2, 68.0], [72.3, 68.0], [72.4, 68.0], [72.5, 69.0], [72.6, 69.0], [72.7, 69.0], [72.8, 70.0], [72.9, 70.0], [73.0, 70.0], [73.1, 71.0], [73.2, 71.0], [73.3, 71.0], [73.4, 71.0], [73.5, 72.0], [73.6, 72.0], [73.7, 72.0], [73.8, 73.0], [73.9, 73.0], [74.0, 74.0], [74.1, 74.0], [74.2, 75.0], [74.3, 75.0], [74.4, 75.0], [74.5, 76.0], [74.6, 76.0], [74.7, 77.0], [74.8, 77.0], [74.9, 77.0], [75.0, 77.0], [75.1, 78.0], [75.2, 78.0], [75.3, 78.0], [75.4, 78.0], [75.5, 79.0], [75.6, 79.0], [75.7, 79.0], [75.8, 80.0], [75.9, 80.0], [76.0, 80.0], [76.1, 80.0], [76.2, 81.0], [76.3, 81.0], [76.4, 81.0], [76.5, 82.0], [76.6, 82.0], [76.7, 82.0], [76.8, 83.0], [76.9, 83.0], [77.0, 84.0], [77.1, 84.0], [77.2, 84.0], [77.3, 84.0], [77.4, 85.0], [77.5, 85.0], [77.6, 85.0], [77.7, 86.0], [77.8, 86.0], [77.9, 86.0], [78.0, 87.0], [78.1, 87.0], [78.2, 87.0], [78.3, 88.0], [78.4, 88.0], [78.5, 88.0], [78.6, 89.0], [78.7, 89.0], [78.8, 89.0], [78.9, 90.0], [79.0, 90.0], [79.1, 91.0], [79.2, 91.0], [79.3, 91.0], [79.4, 91.0], [79.5, 91.0], [79.6, 92.0], [79.7, 92.0], [79.8, 92.0], [79.9, 92.0], [80.0, 93.0], [80.1, 93.0], [80.2, 93.0], [80.3, 93.0], [80.4, 94.0], [80.5, 94.0], [80.6, 94.0], [80.7, 95.0], [80.8, 95.0], [80.9, 95.0], [81.0, 95.0], [81.1, 96.0], [81.2, 96.0], [81.3, 96.0], [81.4, 97.0], [81.5, 97.0], [81.6, 97.0], [81.7, 98.0], [81.8, 98.0], [81.9, 99.0], [82.0, 99.0], [82.1, 99.0], [82.2, 100.0], [82.3, 100.0], [82.4, 100.0], [82.5, 100.0], [82.6, 101.0], [82.7, 101.0], [82.8, 101.0], [82.9, 102.0], [83.0, 102.0], [83.1, 102.0], [83.2, 103.0], [83.3, 103.0], [83.4, 104.0], [83.5, 104.0], [83.6, 105.0], [83.7, 105.0], [83.8, 106.0], [83.9, 106.0], [84.0, 107.0], [84.1, 108.0], [84.2, 108.0], [84.3, 109.0], [84.4, 110.0], [84.5, 111.0], [84.6, 111.0], [84.7, 112.0], [84.8, 112.0], [84.9, 112.0], [85.0, 113.0], [85.1, 114.0], [85.2, 115.0], [85.3, 115.0], [85.4, 116.0], [85.5, 116.0], [85.6, 117.0], [85.7, 117.0], [85.8, 118.0], [85.9, 119.0], [86.0, 119.0], [86.1, 119.0], [86.2, 120.0], [86.3, 121.0], [86.4, 122.0], [86.5, 122.0], [86.6, 123.0], [86.7, 124.0], [86.8, 125.0], [86.9, 125.0], [87.0, 126.0], [87.1, 127.0], [87.2, 127.0], [87.3, 128.0], [87.4, 128.0], [87.5, 129.0], [87.6, 130.0], [87.7, 130.0], [87.8, 131.0], [87.9, 132.0], [88.0, 132.0], [88.1, 133.0], [88.2, 134.0], [88.3, 135.0], [88.4, 136.0], [88.5, 136.0], [88.6, 137.0], [88.7, 138.0], [88.8, 138.0], [88.9, 138.0], [89.0, 139.0], [89.1, 139.0], [89.2, 140.0], [89.3, 141.0], [89.4, 142.0], [89.5, 143.0], [89.6, 144.0], [89.7, 144.0], [89.8, 145.0], [89.9, 147.0], [90.0, 148.0], [90.1, 148.0], [90.2, 149.0], [90.3, 149.0], [90.4, 150.0], [90.5, 151.0], [90.6, 152.0], [90.7, 153.0], [90.8, 155.0], [90.9, 155.0], [91.0, 157.0], [91.1, 157.0], [91.2, 158.0], [91.3, 159.0], [91.4, 159.0], [91.5, 160.0], [91.6, 161.0], [91.7, 161.0], [91.8, 163.0], [91.9, 164.0], [92.0, 164.0], [92.1, 165.0], [92.2, 165.0], [92.3, 166.0], [92.4, 167.0], [92.5, 168.0], [92.6, 168.0], [92.7, 169.0], [92.8, 170.0], [92.9, 171.0], [93.0, 172.0], [93.1, 173.0], [93.2, 174.0], [93.3, 175.0], [93.4, 175.0], [93.5, 177.0], [93.6, 177.0], [93.7, 178.0], [93.8, 179.0], [93.9, 180.0], [94.0, 181.0], [94.1, 183.0], [94.2, 185.0], [94.3, 186.0], [94.4, 187.0], [94.5, 188.0], [94.6, 189.0], [94.7, 191.0], [94.8, 192.0], [94.9, 193.0], [95.0, 194.0], [95.1, 196.0], [95.2, 196.0], [95.3, 197.0], [95.4, 198.0], [95.5, 199.0], [95.6, 200.0], [95.7, 202.0], [95.8, 202.0], [95.9, 203.0], [96.0, 204.0], [96.1, 205.0], [96.2, 205.0], [96.3, 206.0], [96.4, 207.0], [96.5, 209.0], [96.6, 210.0], [96.7, 211.0], [96.8, 213.0], [96.9, 215.0], [97.0, 216.0], [97.1, 218.0], [97.2, 219.0], [97.3, 221.0], [97.4, 222.0], [97.5, 224.0], [97.6, 228.0], [97.7, 232.0], [97.8, 233.0], [97.9, 235.0], [98.0, 239.0], [98.1, 240.0], [98.2, 240.0], [98.3, 240.0], [98.4, 245.0], [98.5, 248.0], [98.6, 251.0], [98.7, 257.0], [98.8, 261.0], [98.9, 266.0], [99.0, 269.0], [99.1, 274.0], [99.2, 280.0], [99.3, 285.0], [99.4, 287.0], [99.5, 299.0], [99.6, 309.0], [99.7, 328.0], [99.8, 341.0], [99.9, 347.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "maxY": 4932.0, "series": [{"data": [[0.0, 4932.0], [300.0, 28.0], [200.0, 236.0], [100.0, 803.0], [500.0, 1.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 500.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 5999.0, "series": [{"data": [[0.0, 5999.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 1.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 89.37983333333362, "minX": 1.74499782E12, "maxY": 89.37983333333362, "series": [{"data": [[1.74499782E12, 89.37983333333362]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74499782E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 4.666666666666667, "minX": 1.0, "maxY": 152.43478260869568, "series": [{"data": [[2.0, 6.888888888888889], [3.0, 4.666666666666667], [4.0, 5.5], [5.0, 6.285714285714286], [6.0, 5.5], [7.0, 11.0], [8.0, 31.5], [9.0, 12.75], [11.0, 12.0], [13.0, 17.8], [14.0, 23.6], [15.0, 13.5], [16.0, 19.75], [17.0, 10.25], [18.0, 11.8], [19.0, 10.5], [20.0, 15.916666666666668], [21.0, 11.90909090909091], [22.0, 9.5], [23.0, 15.25], [24.0, 35.0], [25.0, 16.5], [27.0, 16.666666666666668], [28.0, 27.42857142857143], [29.0, 14.1], [30.0, 17.5], [33.0, 22.5], [32.0, 18.307692307692307], [34.0, 152.43478260869568], [35.0, 73.5666666666667], [36.0, 16.2], [37.0, 25.333333333333332], [38.0, 16.92307692307692], [39.0, 14.350000000000001], [41.0, 9.5], [40.0, 8.666666666666666], [43.0, 32.333333333333336], [42.0, 78.75], [44.0, 18.500000000000004], [45.0, 32.25], [47.0, 27.312499999999996], [46.0, 32.76923076923077], [48.0, 46.687500000000014], [49.0, 44.473684210526315], [50.0, 49.507246376811594], [51.0, 57.636363636363626], [52.0, 22.230769230769234], [53.0, 27.999999999999993], [54.0, 33.2], [55.0, 42.666666666666664], [56.0, 26.733333333333334], [57.0, 38.208333333333336], [58.0, 39.9375], [59.0, 42.37837837837837], [60.0, 31.799999999999994], [61.0, 25.27777777777778], [62.0, 38.18421052631579], [63.0, 37.99999999999999], [64.0, 76.9090909090909], [65.0, 82.66666666666667], [66.0, 52.19354838709676], [67.0, 27.76923076923077], [68.0, 22.384615384615387], [69.0, 42.792452830188694], [70.0, 28.571428571428573], [71.0, 56.705882352941174], [72.0, 41.75], [73.0, 33.897959183673464], [74.0, 42.714285714285715], [75.0, 65.59999999999998], [76.0, 30.525], [77.0, 54.33333333333332], [78.0, 62.304347826086946], [79.0, 43.71874999999999], [80.0, 50.655172413793096], [81.0, 42.40625], [82.0, 59.53846153846154], [83.0, 48.4], [84.0, 69.0], [86.0, 61.294642857142854], [87.0, 61.07246376811593], [85.0, 52.88000000000001], [88.0, 37.74999999999999], [89.0, 56.517647058823506], [90.0, 59.63636363636364], [91.0, 73.77419354838706], [92.0, 62.89473684210528], [93.0, 61.64285714285714], [94.0, 73.06896551724134], [95.0, 48.60204081632653], [96.0, 56.4113475177305], [97.0, 53.97169811320756], [98.0, 61.62068965517239], [99.0, 66.54508196721306], [100.0, 66.53897482559917], [1.0, 5.4]], "isOverall": false, "label": "HTTP Request", "isController": false}, {"data": [[89.37983333333362, 59.47449999999994]], "isOverall": false, "label": "HTTP Request-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 35081.55, "minX": 1.74499782E12, "maxY": 60900.0, "series": [{"data": [[1.74499782E12, 60900.0]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.74499782E12, 35081.55]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74499782E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 59.47449999999994, "minX": 1.74499782E12, "maxY": 59.47449999999994, "series": [{"data": [[1.74499782E12, 59.47449999999994]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74499782E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 58.87299999999995, "minX": 1.74499782E12, "maxY": 58.87299999999995, "series": [{"data": [[1.74499782E12, 58.87299999999995]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74499782E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.07549999999999978, "minX": 1.74499782E12, "maxY": 0.07549999999999978, "series": [{"data": [[1.74499782E12, 0.07549999999999978]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74499782E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 4.0, "minX": 1.74499782E12, "maxY": 533.0, "series": [{"data": [[1.74499782E12, 533.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.74499782E12, 148.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.74499782E12, 268.9899999999998]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.74499782E12, 193.94999999999982]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.74499782E12, 4.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.74499782E12, 45.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74499782E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 23.0, "minX": 483.0, "maxY": 52.0, "series": [{"data": [[1357.0, 52.0], [1552.0, 49.0], [1624.0, 44.0], [483.0, 41.0], [984.0, 23.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 1624.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 22.0, "minX": 483.0, "maxY": 52.0, "series": [{"data": [[1357.0, 52.0], [1552.0, 49.0], [1624.0, 44.0], [483.0, 40.0], [984.0, 22.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 1624.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 100.0, "minX": 1.74499782E12, "maxY": 100.0, "series": [{"data": [[1.74499782E12, 100.0]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74499782E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 100.0, "minX": 1.74499782E12, "maxY": 100.0, "series": [{"data": [[1.74499782E12, 100.0]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74499782E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 100.0, "minX": 1.74499782E12, "maxY": 100.0, "series": [{"data": [[1.74499782E12, 100.0]], "isOverall": false, "label": "HTTP Request-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74499782E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 100.0, "minX": 1.74499782E12, "maxY": 100.0, "series": [{"data": [[1.74499782E12, 100.0]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74499782E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

