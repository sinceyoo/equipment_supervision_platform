var cpuOptions;
var memoryOptions;
var diskOptions;
var networkOptions;

var gaugeOpts = {
	  lines: 12, // The number of lines to draw
	  angle: 0.05,//0.15, // The length of each line
	  lineWidth: 0.34, // The line thickness
	  pointer: {
	    length: 0.9, // The radius of the inner circle
	    strokeWidth: 0.035, // The rotation offset
	    color: '#000000' // Fill color
	  },
	  limitMax: 'false',   // If true, the pointer will not go past the end of the gauge
	  colorStart: '#FAD07D',//'#CF0000',   // Colors
	  colorStop: '#FAD07D',//'#DA0D0D',    // just experiment with them
	  strokeColor: '#61A5FF',   // to see which ones work best for you
	  generateGradient: true
};

var cpuGauge;
function initCpuGauge(targetId) {
	if(cpuGauge==null) {
		var target = document.getElementById(targetId); // your canvas element
		cpuGauge = new Gauge(target).setOptions(gaugeOpts); // create sexy gauge!
		cpuGauge.maxValue = 100; // set max gauge value
	}	
}

var memoryGauge;
function initMemoryGauge(targetId) {
	if(memoryGauge==null) {
		var target = document.getElementById(targetId); // your canvas element
		memoryGauge = new Gauge(target).setOptions(gaugeOpts); // create sexy gauge!
		memoryGauge.maxValue = 100; // set max gauge value
	}	
}

var cpuChart;
var memoryChart;
var diskChart;
var networkChart;

function launchCpuOptions(times, idles, combineds) {
	cpuOptions = {
		 tooltip : {
 	        trigger: 'axis',
 	        axisPointer: {
 	            type: 'cross',
 	            label: {
 	                backgroundColor: '#6a7985'
 	            }
 	        }
 	    },
		legend: {
	        data:['空闲率','占用率']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
		xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: times
	    },
	    yAxis: {
	        boundaryGap: [0, '50%'],
	        type: 'value',
	        max: 105
	    },
	    series : [
	        {
	            name:'空闲率',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:idles
	        },
	        {
	            name:'占用率',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            data:combineds
	        }
	    ]
	}
}

function launchMemoryOptions(times, usedPercents) {
	memoryOptions = {
		 tooltip : {
 	        trigger: 'axis',
 	        axisPointer: {
 	            type: 'cross',
 	            label: {
 	                backgroundColor: '#6a7985'
 	            }
 	        }
 	    },
		legend: {
	        data:['使用率']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
		xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: times
	    },
	    yAxis: {
	        boundaryGap: [0, '50%'],
	        type: 'value',
	        max: 105
	    },
	    series : [
	        {
	            name:'使用率',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            stack: '总量',
	            areaStyle: {normal: {}},
	            itemStyle:{color:'#FFD85C'},
	            data:usedPercents
	        }
	    ]
	}
}

function launchNetworkOptions(times, rxBytes, txBytes) {
	networkOptions = {
		 tooltip : {
 	        trigger: 'axis',
 	        axisPointer: {
 	            type: 'cross',
 	            label: {
 	                backgroundColor: '#6a7985'
 	            }
 	        }
 	    },
		legend: {
	        data:['接收字节数','发送字节数']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
		xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: times
	    },
	    yAxis: {
	        boundaryGap: [0, '50%'],
	        type: 'value'
	    },
	    series : [
	        {
	            name:'接收字节数',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            stack: '总量',
	            data:rxBytes
	        },
	        {
	            name:'发送字节数',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            stack: '总量',
	            data:txBytes
	        }
	    ]
	}
}

function launchDiskOptions(parts, totals, useds) {
	diskOptions = {
		    legend: {
		        data:['总大小','使用量']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : parts
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'总大小',
		            type:'bar',
		            data:totals
		        },
		        {
		            name:'使用量',
		            type:'bar',
		            data:useds
		        }
		    ]
		};
}

function initCpuMonitor(targetId, gaugeTargetId, style, times, idles, combineds) {
	// 基于准备好的dom，初始化echarts实例
	if(cpuChart!=null) {
		cpuChart.dispose();
	}
	cpuChart = echarts.init(document.getElementById(targetId), style);
	
	launchCpuOptions(times, idles, combineds);
    // 使用刚指定的配置项和数据显示图表。
	cpuChart.setOption(cpuOptions);
	
	initCpuGauge(gaugeTargetId);
	if(combineds!=null && combineds.length>0) {
		cpuGauge.set(combineds[combineds.length-1]);
	}
}

function initMemoryMonitor(targetId, gaugeTargetId, style, times, usedPercents) {
	if(memoryChart!=null) {
		memoryChart.dispose();
	}
	memoryChart = echarts.init(document.getElementById(targetId), style);
	
	launchMemoryOptions(times, usedPercents);
	memoryChart.setOption(memoryOptions);
	
	initMemoryGauge(gaugeTargetId);
	if(usedPercents!=null && usedPercents.length>0) {
		memoryGauge.set(usedPercents[usedPercents.length-1]);
	}
}

function initNetworkMonitor(targetId, style, times, rxBytes, txBytes) {
	if(networkChart!=null) {
		networkChart.dispose();
	}
	networkChart = echarts.init(document.getElementById(targetId), style);
	
	launchNetworkOptions(times, rxBytes, txBytes);
	networkChart.setOption(networkOptions);
	
}

function initDiskMonitor(targetId, style, parts, totals, useds) {
	if(diskChart!=null) {
		diskChart.dispose();
	}
	diskChart = echarts.init(document.getElementById(targetId), style);
	
	launchDiskOptions(parts, totals, useds);
	diskChart.setOption(diskOptions);
	
}

var monitorIp;
function initAll(ip, cpu_chart_id, memory_chart_id, network_chart_id, disk_chart_id, cpu_gauge_id, memory_gauge_id, style) {
	monitorIp = ip;
	$.ajax({url:'hostMonitor/monitor?ip='+monitorIp, success:function(rest){
		initCpuMonitor(cpu_chart_id, cpu_gauge_id, style, rest.cpu.times, rest.cpu.idles, rest.cpu.combineds);
		initMemoryMonitor(memory_chart_id, memory_gauge_id, style, rest.memory.times, rest.memory.usedPercents);
		initNetworkMonitor(network_chart_id, style, rest.network.times, rest.network.rxBytes, rest.network.txBytes);
		initDiskMonitor(disk_chart_id, style, rest.disk.parts, rest.disk.totals, rest.disk.useds);
		monitor() ;
	}});
}
function monitor() {
	$.ajax({url:'hostMonitor/monitor?ip='+monitorIp, success:function(rest){
		cpuChart.setOption({
			xAxis: {data: rest.cpu.times},
		    series : [{data:rest.cpu.idles},{data:rest.cpu.combineds}]
		});
		cpuGauge.set(rest.cpu.combineds[rest.cpu.combineds.length-1]);
		
		memoryChart.setOption({
			xAxis: {data: rest.memory.times},
		    series : [{data:rest.memory.usedPercents}]
		});
		memoryGauge.set(rest.memory.usedPercents[rest.memory.usedPercents.length-1]);
		
		networkChart.setOption({
			xAxis: {data: rest.network.times},
		    series : [{data:rest.network.rxBytes},{data:rest.network.txBytes}]
		});
		
		diskChart.setOption({
		    series : [{data:rest.disk.totals},{data:rest.disk.useds}]
		});
		
		setTimeout("monitor()",1000)
	}});
}

