let $Grid        = null;
$(function () {
    // 초기 설정 및 수행
    init();
});

/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    // 초기 화면 구성 ----------------

    // dropdown 생성 ----------------
    DropdownUtil.makeCompList($('.d-companyCd'));  // 회사코드

    // event 연결 ----------------
    $("#searchForm").find('.btn-search').on('click', function(e){ e.preventDefault(); loadGrid(); });
    $('.btn-wrap .btn-add').on('click', function(){ goAddAuthuser(); });

    //더블클릭시 권한사용자등록화면 이동
    $('#listDataTableAuth tbody').on('dblclick', 'tr', function (e) {
        goAddAuthuser();
    });

    setTimeout(function() {
        loadGrid();
    }, 300);
}

/**
 * 그리드 옵션
 */
let GRID_OPTIONS = {
    columns     : [
        { data: 'authSeq',    className: "select-checkbox",
            'render': function (data, type, full, meta) {
                return '<input type="radio" value="'+data+'" />';
            }
        },
        { data: 'companyNm',   className: "text-center"   },
        { data: 'authNm',   className: "text-center"   },
        { data: 'authDesc',   className: "text-left"   },
        { data: 'authUserCnt',   className: "text-center" },
    ],
};

/**
 * Grid 구성
 */
let loadGrid = function(){
    if(ObjectUtil.isEmpty($('#searchForm').find('#companyCd').val())) {
        alert('회사를 선택해 주세요.');
        $('#searchForm').find('.d-companyCd').focus();
        return;
    }

    let gridOptions = $.extend(true, {}, GRID_OPTIONS);
    let url = '/kmacvoc/v1/auth/list';
    let param = $('#searchForm').form('get.values');

    gridUtil.loadGrid("listDataTableAuth", gridOptions, url, param);
};

/**
 * 권한사용자등록화면 이동
 */
let goAddAuthuser =  function(){
    if($selectedRowData.authSeq == undefined) {
        alert('목록을 선택해 주세요.');
        return;
    }
    localStorage.setItem("authSeq", $selectedRowData.authSeq);
    localStorage.setItem("companyCd", $('#searchForm').find('#companyCd').val());
    goPage('/system/authuserdetail');
}
