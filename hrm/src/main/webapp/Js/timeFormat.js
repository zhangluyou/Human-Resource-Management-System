function TimeFormatToSQL(strTime) { 
var strResult = ""; 
var strTemp = ""; 
for (var i = 0; i < strTime.length; i++) { 
strTemp = strTime.substr(i, 1); 
if (strTemp == "年" || strTemp == "月") 
strResult += "-"; 
else 
if (strTemp == "日" || strTemp == "秒") { 
if (strTemp == "日") 
strResult += "|"; 
else 
strResult += ""; 
} 
else 
if (strTemp == "时" || strTemp == "分") 
strResult += ":"; 
else 
strResult += strTemp; 
} 
var strArgDateTime = strResult.split('|'); //此时的时间格式可能为2010-11-11 11: 或2010-11-11 11等格式 
if (strArgDateTime.length == 1) { 
//日期部分进行处理 
var strArgDate = strArgDateTime[0].split('-'); //此时对时间部分进行处理,可能为11: 11 或11:00等格式 
if (strArgDate.length == 2) { 
if (strArgDate[1].length < 1) 
strResult = strArgDate[0]; 
else 
strResult = strArgDateTime[0] + "-01"; 
} else 
if (strArgDate.length == 3) { 
if (strArgDate[2].length < 1) 
strResult = strArgDate[0] + "-" + strArgDate[1] + "-01"; 
} 
} 
else 
if (strArgDateTime.length == 2) { 
//时间部分进行处理 
var strArgTime = strArgDateTime[1].split(':'); //此时对时间部分进行处理,可能为11: 11 或11:00等格式 
if (strArgTime.length == 1) { 
strResult = strArgDateTime[0] + " " + strArgDateTime[1] + ":00:00" 
} else 
if (strArgTime.length == 2) { 
if (strArgTime[1].length < 1) 
strResult = strArgDateTime[0] + " " + strArgDateTime[1] + "00" 
else 
strResult = strArgDateTime[0] + " " + strArgDateTime[1] + ":00" 
} else 
if (strArgTime.length == 3) { 
if (strArgTime[2].length < 1) 
strResult = strArgDateTime[0] + " " + strArgDateTime[1] + "00" 
} 
} 
return strResult; 
} 