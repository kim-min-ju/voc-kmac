let ObjectUtil = {
    isEmpty: function(value) {
        if (value === null) return true
        if (typeof value === 'undefined') return true
        if (typeof value === 'string' && value === '') return true
        if (Array.isArray(value) && value.length < 1) return true
        if (typeof value === 'object' && value.constructor.name === 'Object' && Object.keys(value).length < 1 && Object.getOwnPropertyNames(value) < 1) return true
        if (typeof value === 'object' && value.constructor.name === 'String' && Object.keys(value).length < 1) return true // new String()
        return false;
    },
    default: function(value, defValue) {
        return this.isEmpty(value) ? defValue : value
    },
    copy: function(target, source){
        let tempTarget = JSON.parse(JSON.stringify(target));
        let tempSource = JSON.parse(JSON.stringify(source));
        Object.assign(tempTarget, tempSource);

        return tempTarget;
    }
}
let AjaxUtil = {
    get: function (url, param, callback) {
        $.ajax({
            type:"GET",
            dataType:"JSON",
            url : url,
            data : param,
            contentType: "application/json",
            async: true,
            success : function(data) {
                if(data.errorCode == '0000') {
                    if(callback != null) callback(data);
                }
            },
            complete: function() {
            },
            error : function(xhr) {
            }
        });
    }
}

let StringUtil = {
    numberWithCommas: function(str, step) {
        if(typeof str === 'number') str = str.toString();
        let reg = new RegExp('\\B(?=(\\d{' + step + '})+(?!\\d))', 'g')
        return str.replace(reg, ",");
    }
}


let Util = {
    /**----------------------------------------------------------------------------------*
     * @Function    : Util.getToday(delimiter);
     * @Desc        : 현재날짜
     * @param       : delimiter : 구분자
     * ---------------------------------------------------------------------------------*/
    getToday : function(delimiter) {
        if(ObjectUtil.isEmpty(delimiter)) delimiter = "";
        let date = new Date();
        return date.getFullYear() + delimiter + ("0" + (date.getMonth()+1)).slice(-2) + delimiter + ("0" + date.getDate()).slice(-2);
    },
    /**----------------------------------------------------------------------------------*
     * @Function    : Util.numberFormat(price);
     * @Desc        : 금액을 천 단위로 (,)찍어서 리턴
     * @param       : text
     * ---------------------------------------------------------------------------------*/
    numberFormat : function(number) {
        if(ObjectUtil.isEmpty(number) || isNaN(number)) return number;
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
    /**----------------------------------------------------------------------------------*
     * @Function 	: Util.getTwoDigit(n);
     * @Desc 		: 숫자 2자리로 변환
     * @param 		: n - 변경할 숫자
     * ---------------------------------------------------------------------------------*/
    getTwoDigit : function(n) {
        return n > 9 ? "" + n : "0" + n;
    },
    /**----------------------------------------------------------------------------------*
     * @Function 	: Util.getCurrentMD();
     * @Desc 		: 메인/영화 페이지에서 표시하는 날짜 및 시간
     * ---------------------------------------------------------------------------------*/
    getCurrentMD : function(addDay) {
        //var week = new Array('일', '월', '화', '수', '목', '금', '토');
        var today = new Date();
        var pDate  = (addDay) ? (new Date(today.setDate(today.getDate() + (addDay) ))) : today;
        
        var month = Util.getTwoDigit(pDate.getMonth() + 1);
        var day   = Util.getTwoDigit(pDate.getDate());
        //var dayName = week[pDate.getDay()];
        //var hour = Util.getTwoDigit(pDate.getHours());
        //var minutes = Util.getTwoDigit(pDate.getMinutes());
        return month + "월" + day + "일";
        //var divider = "&#160;";
        //$(".time-wrap .day").html(month + "/" + day + divider + "(" + dayName + ")");
        //$(".time-wrap .time").text(hour + ":" + minutes);
    },
}

let UIUtil = {
    /**----------------------------------------------------------------------------------*
     * @Function    : UIUtil.loadDatepicker(jqueryObj);
     * @Desc        : datepicker 적용
     * @param       : $targetObjects - jquery 객체
     * 예) UIUtil.loadDatepicker($("#selectedDt"));
     * ---------------------------------------------------------------------------------*/
    loadDatepicker : function($targetObjects, addOptions){

        let defaultDatepickerOptions = {
            //달력 날짜 형태
              dateFormat: 'yy.mm.dd'
            //빈 공간에 현재월의 앞뒤월의 날짜를 표시 
            , showOtherMonths: true 
            // 월- 년 순서가아닌 년도 - 월 순서
            , showMonthAfterYear:true 
            //option값 년 선택 가능
            , changeYear: true 
            //option값  월 선택 가능
            , changeMonth: true 
            //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
            , showOn: "both" 
            //버튼 이미지 경로
            , buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" 
            //버튼 이미지만 깔끔하게 보이게함
            , buttonImageOnly: true 
            //버튼 호버 텍스트
            , buttonText: "선택" 
            //달력의 년도 부분 뒤 텍스트
            , yearSuffix: "년" 
            //달력의 월 부분 텍스트
            , monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] 
            //달력의 월 부분 Tooltip
            , monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] 
            //달력의 요일 텍스트
            , dayNamesMin: ['일','월','화','수','목','금','토'] 
            //달력의 요일 Tooltip
            , dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] 
            //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            , minDate: "-5Y" 
            //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
            , maxDate: "+5y" 
        };

        let dpOptions = (addOptions && !ObjectUtil.isEmpty(addOptions)) ? $.extend(true, {}, defaultDatepickerOptions, addOptions) : defaultDatepickerOptions;

        if($targetObjects.length){
            $targetObjects.datepicker(dpOptions);
        }
    },
}