let $Grid        = null;

$(function () {
    // 초기 설정 및 수행
    init();
});

/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    let $frm = $('#searchForm');

    // event 연결 ----------------
    $('.btn-search').on('click', function(){ loadGrid(); });
    $('.ui.checkbox').on('click', function(){ setVocType($(this).attr('id')); });

    // dropbox data setting ----------------
    DropdownUtil.makeCodeList('COM999','RCPT_CHNN_CD', $frm.find('.d-rcptChnnCd'));     //접수채널코드
    DropdownUtil.makeCompList($frm.find('.d-companyCd'));   // 회사코드
    makeCodeVocType($('#searchForm'));  //VOC유형코드

    //loadGrid();
}

/**
 * 그리드 옵션
 */
let numberRender = $.fn.dataTable.render.number(',');   // 숫자 3자리마다 ',' 적용
let GRID_OPTIONS = {
    columns     : [
        { data: 'vocTypeNm1',       className: "text-center"   },
        { data: 'totalCnt',         className: "text-center"   ,render: numberRender},
        { data: 'totalYoyCnt',      className: "text-center"   ,render: numberRender},
        { data: 'complimentCnt',    className: "text-center"   ,render: numberRender},
        { data: 'complimentRate',   className: "text-center"   ,render: numberRender},
        { data: 'complimentYoyCnt', className: "text-center"   ,render: numberRender},
        { data: 'complaintCnt',     className: "text-center"   ,render: numberRender},
        { data: 'complaintRate',    className: "text-center"   ,render: numberRender},
        { data: 'complaintYoyCnt',  className: "text-center"   ,render: numberRender},
        { data: 'suggestionCnt',    className: "text-center"   ,render: numberRender},
        { data: 'suggestionRate',   className: "text-center"   ,render: numberRender},
        { data: 'suggestionYoyCnt', className: "text-center"   ,render: numberRender},
        { data: 'inquiryCnt',       className: "text-center"   ,render: numberRender},
        { data: 'inquiryRate',      className: "text-center"   ,render: numberRender},
        { data: 'inquiryYoyCnt',    className: "text-center"   ,render: numberRender},
    ],
    paging: false
};

/**
 * Grid 구성
 */
let loadGrid = function(){
    let $frm = $('#searchForm');

    if(ObjectUtil.isEmpty($frm.find('#companyCd').val())) {
        alert('회사를 선택해 주세요.'); return;
    }
    if(ObjectUtil.isEmpty($frm.find('#regDtStart').val()) || ObjectUtil.isEmpty($frm.find('#regDtEnd').val())) {
        alert('등록일을 선택해 주세요.'); return;
    }

    let gridOptions = $.extend(true, {}, GRID_OPTIONS);
    let url = '/kmacvoc/v1/statistics/list/voctype';
    let param = $('#searchForm').form('get.values');

    param.regDtStart = $frm.find('#regDtStart').val();
    param.regDtEnd = $frm.find('#regDtEnd').val();
    param.vocTypeCd2Yn = param.vocTypeCd2Yn == false ? '' : 'Y';
    param.vocTypeCd3Yn = param.vocTypeCd3Yn == false ? '' : 'Y';

    console.log('param',param);

    $Grid = gridUtil.loadGrid("listDataTableVoctype", gridOptions, url, param);
};

/**
 * VOC 유형 컨트롤
 */
let setVocType = function(id){
    if($('.chk-vocTypeCd'+id).hasClass('checked')) {
        $('.d-vocTypeCd'+id).removeClass('disabled');
    } else {
        $('.d-vocTypeCd'+id).addClass('disabled');
        $('.d-vocTypeCd'+id).dropdown('clear');
    }
}
