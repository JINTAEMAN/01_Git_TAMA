<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%
String ref = request.getHeader("referer");
String param = request.getParameter("gridID");
if(ref == null || ref.equals("") || param == null || param.equals("")) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return;
}
// 사용자 세션 정보를 이용한  접근제거 필요한 경우 아래 추가

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:xf="http://www.w3.org/2002/xforms" xmlns:ev="http://www.w3.org/2001/xml-events" xmlns:w2="http://www.inswave.com/websquare">
<head>
<meta http-equiv=Content-Type content="text/html;charset=UTF-8" />
<meta http-equiv=Cache-Control content=No-Cache />
<meta http-equiv=Pragma content=No-Cache />
<title>FILE UPLOAD</title>
<script type="text/javascript" src="../../../message/htmlCommon.js"></script>
<script language="JavaScript">
    var osName = "";
    var domain = "";
    var processMsg = "";
    var maxFileSize = -1;
    var vappend;
    var useModalDisable = "";
    var useMaxByteLength = "";
    var delim = "";

    var uploadType;
    var dataListStatus;
    var columnIds;
    var gridID;
    var dataList;
    var wframeId;
    var callbackFunction = "";
    var scopeId = "";

    // 다국어
    var Upload_ignore_spaces = "";
    var Upload_include_spaces = "";
    var Upload_advanced = "";
    var Upload_include = "";
    var Upload_not_include = "";
    var Upload_fill_hidden = "";
    var Upload_sheet_no = "";
    var Upload_starting_row = "";
    var Upload_starting_col = "";
    var Upload_header = "";
    var Upload_footer = "";
    var Upload_file = "";
    var Upload_pwd = "";

    var Upload_msg2 = "";
    var Upload_msg3 = "";
    var Upload_msg4 = "";
    var Upload_msg5 = "";
    var Upload_msg8 = "";
    var Upload_msg9 = "";
    var Upload_msg10 = "";
    var Upload_msg11 = "";
    var Upload_msg12 = "";
    var Upload_msg13 = "";
    var Upload_msg14 = "";
    var Upload_msg15 = "";
    var Upload_msg16 = "";
    var Upload_msg17 = "";
    var Grid_warning9 = "";
    
    window.onload = doInit;
    window.onbeforeunload = doFinish;
    function doInit() {

        var uploadInfo;
        try {
            domain = getParameter("domain");
            if( domain ) {
                document.domain = domain;
            }

            // 팝업 사이즈 보정
            if(navigator.userAgent.indexOf("Windows") != -1) {
                osName = "window";
            } else if(navigator.userAgent.indexOf("Macintosh") != -1) {
                osName = "mac";
            }

            var sizeInfo = crossBrowserSize();
            window.resizeTo( sizeInfo.width, sizeInfo.height );

            multi = getParameter("multi");
            document.__uploadForm__.multi.value = multi;

            uploadInfo = opener.JSON.parse( opener.WebSquare._excelMultiInfo );
            if(uploadInfo.useModalDisable == "true") {
                opener.WebSquare.layer.showModal();
                useModalDisable = "true";
            }
            vappend = uploadInfo.append;
            var actionUrl = uploadInfo.action;
            processMsg = opener.WebSquare.text.BASE64URLDecoder(uploadInfo.processMsg);
            delim = uploadInfo.delim;

            dataListStatus = uploadInfo.status;
            uploadType = uploadInfo.uploadType;
            columnIds = uploadInfo.columnIds;
            gridID = uploadInfo.gridID;
            dataList = uploadInfo.dataList;
            wframeId = uploadInfo.wframeId;
            callbackFunction = uploadInfo.callbackFunction;
            scopeId = uploadInfo.scopeId

            Upload_ignore_spaces = opener.WebSquare.language.getMessage( "Upload_ignore_spaces" ) || "공백무시";
            Upload_include_spaces = opener.WebSquare.language.getMessage( "Upload_include_spaces" ) || "공백포함";
            Upload_advanced = opener.WebSquare.language.getMessage( "Upload_advanced" ) || "고급설정";
            Upload_hidden_values = opener.WebSquare.language.getMessage( "Upload_hidden_values" ) || "히든값유무";
            Upload_include = opener.WebSquare.language.getMessage( "Upload_include" ) || "포함";
            Upload_not_include = opener.WebSquare.language.getMessage( "Upload_not_include" ) || "미포함";
            Upload_fill_hidden = opener.WebSquare.language.getMessage( "Upload_fill_hidden" ) || "히든 채움";
            Upload_sheet_no = opener.WebSquare.language.getMessage( "Upload_sheet_no" ) || "시트 No";
            Upload_starting_row = opener.WebSquare.language.getMessage( "Upload_starting_row" ) || "시작 row";
            Upload_starting_col = opener.WebSquare.language.getMessage( "Upload_starting_col" ) || "시작 col";
            Upload_header = opener.WebSquare.language.getMessage( "Upload_header" ) || "헤더 유무";
            Upload_footer = opener.WebSquare.language.getMessage( "Upload_footer" ) || "푸터 유무";
            Upload_file = opener.WebSquare.language.getMessage( "Upload_file" ) || "파일 업로드";
            Upload_fill = opener.WebSquare.language.getMessage( "Upload_fill" ) || "채움";
            Upload_ignore = opener.WebSquare.language.getMessage( "Upload_ignore" ) || "무시";
            Upload_pwd = opener.WebSquare.language.getMessage( "Upload_pwd" ) || "비밀번호";

            Upload_msg2 = opener.WebSquare.language.getMessage( "Upload_msg2" ) || "파일 사이즈가 제한 용량을 초과 하였습니다.";
            Upload_msg3 = opener.WebSquare.language.getMessage( "Upload_msg3" ) || "정상적으로 처리 되지 않았습니다.";
            Upload_msg4 = opener.WebSquare.language.getMessage( "Upload_msg4" ) || "FileType에 맞지 않는 File의 확장자입니다.";
            Upload_msg5 = opener.WebSquare.language.getMessage( "Upload_msg5" ) || "그리드 반영에 실패하였습니다";
            Upload_msg8 = opener.WebSquare.language.getMessage( "Upload_msg8" ) || "비밀번호가 일치하지 않습니다.";
            Upload_msg9  = opener.WebSquare.language.getMessage( "Upload_msg9" ) || "허용하지 않는 확장자 입니다.";
            Upload_msg10 = opener.WebSquare.language.getMessage( "Upload_msg10" ) || "DRM 연계시 오류가 발생하였습니다.";
            Upload_msg11 = opener.WebSquare.language.getMessage( "Upload_msg11" ) || "업로드 제한 건수를 초과하였습니다.";
            Upload_msg12 = opener.WebSquare.language.getMessage( "Upload_msg12" ) || "유효하지 않은 엑셀 형식입니다.";
            Upload_msg13 = opener.WebSquare.language.getMessage( "Upload_msg13" ) || "유효하지 않은 셀 서식입니다.";
            Upload_msg14 = opener.WebSquare.language.getMessage( "Upload_msg14" ) || "업로드 셀건수제한을 초과하였습니다.";
            Upload_msg15 = opener.WebSquare.language.getMessage( "Upload_msg15" ) || "파일이 암호화되어 있습니다.";
            Upload_msg16 = opener.WebSquare.language.getMessage( "Upload_msg16" ) || "Excel 5.0/7.0은 지원하지 않습니다.";
            Upload_msg17 = opener.WebSquare.language.getMessage( "Upload_msg17" ) || "지정 sheet가 존재하지 않습니다.";

            maxFileSize = uploadInfo.maxFileSize;
            maxFileSize = parseInt( maxFileSize, 10 );
            Grid_warning9 = opener.WebSquare.language.getMessage( "Grid_warning9", maxFileSize ) || "전송 data가 제한 크기를 초과 하였습니다.\n 제한 크기 : %1 byte";

        } catch (e) {
            alert(e);
        }

        with( document.__uploadForm__ ) {
            action = actionUrl;
        }

        if(isSafari) {
            setTimeout(function() {
                var bottomMargin = parseInt(document.height - document.documentElement.offsetHeight, 10) * -1||0;     
                if( bottomMargin != 0 ) {
                    self.resizeBy(0, bottomMargin);
                }
            }, 1);
        }
    }

    function doFinish() {
        if(useModalDisable == "true") {
            opener.WebSquare.layer.hideModal();
        }
    }

    function upload( thisForm ) {
        try {
            var filename = document.getElementById("filename").value;
            if( !filename || filename =="" ) {
                return false;
            }

            if( maxFileSize != -1 ) {
                var uploadFile = document.getElementById( "filename" );
                if( uploadFile && uploadFile.files ) {
                    if( maxFileSize < uploadFile.files[0].size ) {
                        alert( Grid_warning9 );
                        return;
                    }
                }
            }

            var isXlsFile = filename.toLowerCase().indexOf(".xls") >= 0 || filename.toLowerCase().indexOf(".cell") >= 0 || filename.toLowerCase().indexOf(".ods") >= 0;
            var isXlsType = document.__uploadForm__.action.indexOf("excelToGrid") >= 0;
            var isCSVFile = endsWith(filename.toLowerCase(), ".csv") ||
                            endsWith(filename.toLowerCase(), ".txt");
            var isCSVType = document.__uploadForm__.action.indexOf("csvToXML") >= 0;
            if( !(isXlsFile && isXlsType) && !(isCSVFile && isCSVType) ) {
                alert( Upload_msg4 );
                return false;
            }

            var frm = window.frames;
            var find = false;
            for( var i = 0; i < frm.length; i++ ) {
                if( frm[i].name == thisForm.target ) {
                    find = true;
                }
            }
            
            if( !find ) {
                var layerUP= document.createElement("div");
                var src = "";
                layerUP.style.border="1px solid blue";
                layerUP.style.width="100px";
                layerUP.style.height="100px";
                layerUP.style.visibility = "hidden";
                document.body.appendChild(layerUP);
                src = opener.WebSquare.net.getSSLBlankPage();
                layerUP.innerHTML = "<iframe frameborder='0px' name='" + thisForm.target + "' scrolling='no' style='width:100px; height:100px' " + src + "></iframe>";
            }
            
            showProcessMessage( processMsg );

            document.__uploadForm__.gridStyle.value = opener.WebSquare._excelMultiUploadInfo;
            thisForm.submit();
        } catch(e) {
            alert(e.description);
        }
    }

    function returnData( vData ) {

        if( processMsg != "" ) {
            hideProcessMessage();
        }

        var doc = opener.WebSquare.xml.parse( vData );
        var exception = doc.getElementsByTagName("Exception");

        if( exception.length > 0) {
            var code = getTextNodeValue( doc.getElementsByTagName("deniedCodeList")[0] );
            if( typeof code == "undefined" || code == null || code == "" ) {
                code = "";  
            }

            if( code == "102" ) {
                msg = Upload_msg2;
            } else if( code == "101" ) {
                msg = Upload_msg9;
            } else if( code == "200" ) {
                msg = Upload_msg10;
            } else if( code == "201" ) {
                msg = Upload_msg11;
            } else if( code == "202" ) {
                msg = Upload_msg8;
            } else if( code == "203" ) {
                msg = Upload_msg12;
            } else if( code == "204" ) {
                msg = Upload_msg13;
            } else if( code == "205" ) {
                msg = Upload_msg14;
            } else if( code == "206" ) {
                msg = Upload_msg15;
            } else if( code == "207" ) {
                msg = Upload_msg16;
            } else if( code == "208" ) {
                msg = Upload_msg17;
            } else {
                var msg = getTextNodeValue( doc.getElementsByTagName("message")[0] );
                if( typeof msg == "undefined" || msg == null || msg == "" ) {
                    msg = Upload_msg3;
                }
            }
            
            alert(msg);
        } else {
            var child;
            for( var i = 0; i < gridID.length; i++ ) {
                if( delim != "," ) {
                    var childData = (doc.getElementsByTagName("array"))[i].firstChild.nodeValue;
                    //child = toArray( childData, delim );
                    child = "[\"" + toArray( childData, delim ).join("\",\"") + "\"]";
                } else {
                    child = (doc.getElementsByTagName("array"))[i].firstChild.nodeValue;
                }
                
                if( typeof vappend =="string" ) {
                    vappend = opener.WebSquare.util.getBoolean(vappend);
                }
                
                try {
                    var jsonArray = {
                        columnInfo:columnIds[i].split(","),
                        data:child
                    }

                    if( dataList.length != 0 ) {
                        var dcComp = opener.WebSquare.util.getComponentById(dataList[i], wframeId[i]);
                        var preCnt = dcComp.getRowCount();
                        if( uploadType[i] == 1 || uploadType[i] == 2 ) { // 0:실제데이터, 1:출력그대로, 2: 0+1
                            dcComp.setArrayFile(jsonArray, vappend, gridID[i], uploadType[i]);
                         } else if( uploadType[i] == 0 ) {
                            dcComp.setArray(jsonArray, vappend);
                         }

                         if( dataListStatus[i] == "C" ) {
                            var postCnt = dcComp.getRowCount();
                            if( vappend ) {
                                dcComp.modifyRangeStatus( preCnt, postCnt, "C" );
                            } else {
                                dcComp.modifyRangeStatus( 0, postCnt, "C" );
                            }
                         }
                    }

                    var fileNameDom = document.getElementById("filename");
                    var fileName = fileNameDom.value;
                    var fileNameArr = fileName.split("\\"); //fileName에 대해서 IE에서는 파일 경로가 나오는데 FF chrome은 나오지 않는다. 따라서 '\\'기준으로 나눠준다.
                    opener.window[gridID[i]].fireFileReadEnd( fileNameArr[fileNameArr.length-1] );
                } catch (e) {
                    opener.WebSquare.exception.printStackTrace(e);
                    alert( Upload_msg5 );
                }
            }

            opener.WebSquare._excelMultiInfo = "";
            opener.WebSquare._excelMultiUploadInfo = "";

            if(callbackFunction != "") {
                if(typeof scopeId == "string") {
                    scopeId = scopeId.slice(0, scopeId.length-1);
                }
                var callbackFunc = opener.WebSquare.util.getGlobalFunction(callbackFunction, scopeId);               
                if(callbackFunc() === true) {
                    window.self.close();
                }
            } else {
                window.self.close();
            }
        }
    }
    
    function getTextNodeValue(element) {
        var returnValue = null;
        var retValue = "";
        for(var child=element.firstChild; child!=null; child=child.nextSibling){
            if ( child.nodeType == 3 ) {
                retValue += child.nodeValue;
            }
        }

        if( retValue != "" ) {
            returnValue = retValue;
        }

        return returnValue;
    }

     function crossBrowserSize() {
        var sizeInfo = {
            "height": 204,
            "width": 462
        };

        if(opener.WebSquare.util.isIE(6)) {
            sizeInfo.height = 212;
            sizeInfo.width = 456;
        } else if(opener.WebSquare.util.isIE(7)) {
            sizeInfo.height = 218;
            sizeInfo.width = 457;
        } else if(opener.WebSquare.util.isIE(8)) {
            sizeInfo.height = 218;
            sizeInfo.width = 457;
        }  else if(opener.WebSquare.util.isIE(9)) {
            sizeInfo.height = 204;
            sizeInfo.width = 446;
        }  else if(opener.WebSquare.util.isIE(10)) {
            sizeInfo.height = 204;
            sizeInfo.width = 446;
        } else if(opener.WebSquare.util.isIEAllVersion(11)) {
            sizeInfo.height = 204;
            sizeInfo.width = 462;
        } else if(opener.WebSquare.util.isSpartan()) {
            sizeInfo.height = 178;
            sizeInfo.width = 446;
        } else if(opener.WebSquare.util.isChrome()) {
            if(navigator.userAgent.indexOf("OPR") != -1) {  //opera
                if(osName == "window") {
                    sizeInfo.height = 226;
                    sizeInfo.width = 462;
                } else if(osName == "mac") {
                    sizeInfo.height = 189;
                    sizeInfo.width = 446;
                }
            } else { //chrome
                if(osName == "window") {
                    sizeInfo.height = 201;
                    sizeInfo.width = 462;
                } else if(osName == "mac") {
                    sizeInfo.height = 181;
                    sizeInfo.width = 446;
                }
            }
        } else if(opener.WebSquare.util.isFF()) {
            if(osName == "window") {
                sizeInfo.height = 213;
                sizeInfo.width = 462;
            } else if(osName == "mac") {
                sizeInfo.height = 191;
                sizeInfo.width = 446;
            }
        } else if(opener.WebSquare.util.isSafari()) {
            if(osName == "window") {
                sizeInfo.height = 155;
                sizeInfo.width = 446;
            } else if(osName == "mac") {
                sizeInfo.height = 155;
                sizeInfo.width = 446;
            }
        } else if(opener.WebSquare.util.isOpera()) {
            if(osName == "window") {
                sizeInfo.height = 189;
                sizeInfo.width = 446;
            } else if(osName == "mac") {
                sizeInfo.height = 189;
                sizeInfo.width = 446;
            }
        }

        return sizeInfo;
     }

     function crossBrowserHeight() {
        if(opener.WebSquare.util.isIE(6)) {
            return 119;
        }
        if(opener.WebSquare.util.isIE(7)) {
            return 119;
        } 
        if(opener.WebSquare.util.isIE(8)) {
            return 119;
        } 
        if(opener.WebSquare.util.isIE(9)) {
            return 119;
        } 
        if(opener.WebSquare.util.isIEAllVersion(11)) {
            return 119;
        } 
        if(opener.WebSquare.util.isFF()) {
            return 120;
        } 
        if(opener.WebSquare.util.isChrome()) {
            return 119;
        } 
        if(opener.WebSquare.util.isSafari()) {
            return 119;
        } 
        if(opener.WebSquare.util.isOpera()) {
            return 119;
        } 
        return 119;
    }
    
    function showProcessMessage( processMsg ) {
        
        try {
            if(!processMsg || processMsg == "" ) { 
                return;
            }

            var processbar2_main = document.getElementById( "___processbar2" );
            var processbar2 = document.getElementById( "___processbar2_i" );
            var processMsgURL = opener.WebSquare.core.getConfiguration( "processMsgURL" );
            var processMsgHeight = opener.WebSquare.core.getConfiguration( "processMsgHeight" );
            var processMsgWidth = opener.WebSquare.core.getConfiguration( "processMsgWidth" );
            var processMsgBackground = opener.WebSquare.core.getConfiguration( "processMsgBackground" );
            var processMsgBackgroundColor = opener.WebSquare.core.getConfiguration( "/WebSquare/processMsgBackground/@backgroundColor" );
            if (processMsgBackgroundColor == ""){
                processMsgBackgroundColor = "#112233";  
            }
            if( processMsgURL == "" ) {
                processMsgURL = opener.WebSquare.baseURI + "message/processMsg.html";
            }
            
            processMsgURL = processMsgURL + "?param=" + opener.WebSquare.text.URLEncoder(processMsg);
            
            if( processMsgHeight == "" || processMsgWidth == "" ) {
                processMsgHeight = "74";
                processMsgWidth = "272";
            }

            WebSquare = opener.WebSquare;
            WebSquare.layer.processMsg = processMsg;
            
            if(!processbar2_main){
                var node2Main = document.createElement( "div" );
                node2Main.id = "___processbar2";
                node2Main.className = "w2modal";
                node2Main.style.backgroundColor = processMsgBackgroundColor;
                node2Main.tabIndex = 1;
                if(processMsgBackground == "true"){
                    node2Main.style.visibility = "visible";
                } else{
                    node2Main.style.visibility = "hidden";
                }
                
                node2Main.style.height = document.documentElement.clientHeight + "px";
                document.body.appendChild( node2Main );
                
                var e = e || event;
                if( e.preventDefault ) {
                    if( e.type == "keydown" ) {
                        e.preventDefault();
                    }
                } else {
                    if( e.type == "keydown" ) {
                        e.returnValue = false;
                    }
                }
                
            } else {
                processbar2_main.tabIndex = 1;
                processbar2_main.style.zIndex = 10000;
                processbar2_main.style.display = "block";
                processbar2_main.style.top = "0px";
                processbar2_main.style.left = "0px";
            }

            if( !processbar2 ) {
                var nTop = document.documentElement.scrollTop + document.documentElement.clientHeight/2 - parseInt(processMsgHeight)/2;
                var nLeft = document.documentElement.scrollLeft + document.documentElement.clientWidth/2 - parseInt(processMsgWidth)/2;

                var node2 = document.createElement("div");
                node2.id = "___processbar2_i";
                node2.style.position = "absolute";
                node2.style.top = parseInt(nTop) + "px";
                node2.style.left = parseInt(nLeft) + "px";
                node2.style.overflow = "hidden";
                node2.style.zIndex = 10001;
                node2.style.visibility = "visible";
                node2.style.height = processMsgHeight + "px";
                node2.style.width = processMsgWidth + "px";

                document.body.appendChild( node2 );
                node2.innerHTML = "<iframe frameborder='0' scrolling='no'ß name='__processbarIFrame' style='position:absolute; width:"+processMsgWidth+"px; height:"+ processMsgHeight +"px; top:0px; left:0px' src='" + processMsgURL + "'></iframe>";
                
            } else {
                var nTop = document.documentElement.scrollTop + document.documentElement.clientHeight/2 - parseInt(processMsgHeight)/2;
                var nLeft = document.documentElement.scrollLeft + document.documentElement.clientWidth/2 - parseInt(processMsgWidth)/2;
                processbar2.style.top = parseInt(nTop) + "px";
                processbar2.style.left = parseInt(nLeft) + "px";
                processbar2.style.zIndex = 10001;
                window.frames["__processbarIFrame"].location.replace( processMsgURL );
                processbar2.style.display = "block";
            } 
        } catch( e ) {
        }
    }
    
    function hideProcessMessage() {
        try {
            var processbar2 = document.getElementById( "___processbar2" );
            var processbar2i = document.getElementById( "___processbar2_i" );
            if( typeof processbar2 != "undefined" && processbar2 != null ) {
                processbar2.style.zIndex = -1;
                processbar2.style.display = "none";
                processbar2.tabIndex = "-1";
                processbar2.innerHTML = '';
            }
            if( typeof processbar2i != "undefined" && processbar2i != null ) {
                processbar2i.style.zIndex = -1;
                processbar2i.style.display = "none";
            }
        } catch( e ) {
        }
    }

    function toArray( str, delim ) {
        if( delim == undefined ) {
            delim = "\",\"";
        } else {
            delim = "\"" + delim + "\"";
        }

        if( typeof str != "string") {
            if( str === null ) {
            } else {
            }
            return [];
        }

        var re = /^\[\s*\]$/g;
            
        if( str.match( re) != null ){
            return (new Array());
        }
        
        var splitDataIn = str.split( delim );
        splitDataIn[0] = splitDataIn[0].substr(2);
        splitDataIn[splitDataIn.length-1] = splitDataIn[splitDataIn.length-1].substr(0,((splitDataIn[splitDataIn.length-1]).length-2)); 
        return splitDataIn;
    }
</script>

<style type="text/css">
    html, body {margin:0px; padding:0px; font-family:"맑은 고딕"; font-size:11px;}
    p {margin:0px; padding:0px;}
    img, fieldset {border:0;}
    table {width:100%; background:#fff; border-collapse:collapse; border-spacing:0; empty-cells:show;}
    table caption, table summary {width:0; height:0; font-size:0; line-height:0; overflow:hidden; visibility:hidden;}
    
    .none {display:none;}
    .block {display:block;}

    .wrap {width:444px; min-height:106px; border:1px solid #b3b3b3;}
    .header {height:27px; background:url(images/bg_header.gif) repeat-x left top;}
    .header .title {padding-left:28px; font-weight:bold; line-height:23px; background:url(images/bul_title.gif) no-repeat 11px 6px; float:left;}
    .header .title2 {padding-left:28px; font-weight:bold; line-height:23px; float:left;}
    .header span {padding-right:20px; float:right; display:block;}
    .header span input[type=checkbox] {position:relative; top:1px;}

    .content {padding:15px 10px 12px;}
    .content .filebox {padding:8px 0 0 11px; width:408px; height:33px; border:1px solid #d3d3d3; background:#f6f6f6;}
    .content .filebox input[type=file] {width:397px; height:21px; font-family:Verdana; font-size:12px; background:#fff;}

    .tbl {margin:15px auto 0; width:400px;}
    .tbl th, .tbl td {min-width:100px; height:24px; text-align:left;}
    .tbl th .dot {padding-left:14px; background:url(images/dot.gif) no-repeat left center;}
    .tbl td .ipt {width:74px; height:16px; /*bordeR:1px solid #abadb3;*/}
    .tbl td .sel {width:80px; height:20px;}
    .btn_file {margin-bottom:14px; width:90px; position:relative; left:333px;}
</style>
</head>
<body>
<form name="__uploadForm__" method="post" action="" enctype="multipart/form-data" target="__targetFrame__">
    <div class="wrap">
        <div class="header">
            <p class="title">File Upload</p>
        </div>
        <div class="content">
            <div class="filebox">
                <input type="file" id="filename" name="filename" />
            </div>
        </div>
        
        <div class="foot">
            <p><input type="button" id="sendFILE" name="sendFILE" class="btn_file" value="파일 업로드" onclick="return upload(this.form)" /></p>
            <!-- ie8에서는 form안에 input type="image"가 있으면 form전송이 제대로 되지 않습니다. --> 
        </div>

    </div>
    <input type="hidden" id="gridStyle" name="gridStyle" value="" />
    <input type="hidden" id="multi" name="multi" value="" />
</form>
</body>
</html>