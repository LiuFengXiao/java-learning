
    var index =4;
    function getdata(){
        var carid = document.getElementById("carid").value.trim();
        console.log(carid);
        $.ajax({
            url: "/getinfo",
            data: {"id": index, "carid": carid},
            type: "POST",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.conId=="255") alert("无数据！");
                else {
                    //  data = jQuery.parseJSON(data);  //dataType指明了返回数据为json类型，故不需要再反序列化
                    var ctx = document.getElementById('myChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'bar',   //  线形图：line、条形图：bar 、雷达图：radar、馅饼和甜甜圈图表：pie、极区面积图：polarArea、汽泡：bubble
                        data: {
                            labels: ['控制周期', '车速', '中值', '舵机控制量', '右电机控制量', '左电机控制量'],
                            datasets: [{
                                label: '# 小车运动数据',
                                data: [data.conId, data.speed, data.zhongZhi, data.pid, data.pwmA, data.pwmB],
                                // data: [3,4, 5, 6, 7, 8],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            }
                        }
                    });
                }
            }
        });
        index+=1;
    }
/*
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',   //  线形图：line、条形图：bar 、雷达图：radar、馅饼和甜甜圈图表：pie、极区面积图：polarArea、汽泡：bubble
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [3,4, 3, 5, 2, 3],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

 */
