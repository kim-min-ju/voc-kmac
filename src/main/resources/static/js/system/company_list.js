let $Grid        = null;

/**
 * 그리드 옵션
 */
let GRID_OPTIONS = {
    columns     : [
        { data: 'vocSeq',    className: "select-checkbox",
            'render': function (data, type, full, meta) {
                return '<input type="radio" value="'+data+'" />';
            }
        },
        { data: 'companyNm',   className: "text-center"   },
        { data: 'custNo',   className: "text-center"   },
        { data: 'custNm',   className: "text-center"   },
        { data: 'telNo',   className: "text-center"   },
        { data: 'emailAddr',   className: "text-center"   },
        { data: 'regDt',   className: "text-center"   },
        { data: 'vocCnt',   className: "text-center"   },
    ],
    //scrollY             : '300px',
    //scrollCollapse      : true,
    //scrollX: '100%',
    //scrollXInner: '2300px',
};

$(function () {
    // 초기 설정 및 수행
    init();
});


/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    // 초기 화면 구성 ----------------
    //$('.modal-user').modal('hide').modal('hide dimmer');

    // datepicker loading 적용
    // UIUtil.loadDatepicker($("#selectedDt"));

    // event 연결 ----------------
    $('.btn-search').on('click', function(){ ajaxLoadData(); });
    $('.btn-wrap .btn-add').on('click', function(){ goAddCust(); });

    // dropbox data setting ----------------
    //makeDropdownList('USE_YN', $('#registForm').find('.d-retireYn'));
    //makeDropdownList('USE_YN', $('#registForm').find('.d-useYn'));

    ajaxLoadData();

}

/**
 * 정보 조회 => grid를 그린다.
 */
let ajaxLoadData = function(){
    let param = $('#searchForm').form('get.values');

    AjaxUtil.get(
        '/kmacvoc/v1/customer/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                // grid를 그린다.
                loadGrid(result.data.list);
            }
        }
    );
}

/**
 * Grid 구성
 *
 * @param loadDataSet grid를 그릴 dataset
 */
let loadGrid = function(loadDataSet){

    let gridOptions = $.extend(true, {}, GRID_OPTIONS);

    // gird 생성시.
    if($Grid == null){
        gridOptions.data = loadDataSet;

        // grid 생성
        $Grid = gridUtil.loadGrid("listDataTableVoc", gridOptions);
    }
    // reload 시.
    else {
        // ajax가 정의 되어 있을 때
        if(gridOptions.ajax){
            $Grid.ajax.reload();
        }
        // ajax가 정의되지 않고 dataSet으로 그릴 때
        else {
            $Grid.clear();                  // clear
            $Grid.rows.add(loadDataSet);    // Add new data
            $Grid.columns.adjust().draw();  // Redraw the DataTable
        }
    }
};

/**
 * 등록/수정을 위한 고객등록화면 이동
 */
let goAddCust = function(){
    $(location).attr('href', '/voc/custdetail');
}

/**
 * 사용자상세 팝업
 */
let popDtlUser =  function(){

}

