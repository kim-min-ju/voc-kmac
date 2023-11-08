let $Grid        = null;

let GRID_OPTIONS = {
    columns     : [
        { data: 'vocSeq',    className: "select-checkbox",
            'render': function (data, type, full, meta) {
                return '<input type="radio" value="'+data+'" />';
            }
        },
        { data: 'sensSpecYn',   className: "text-center"   },
        { data: 'vocCaseNm',   className: "text-center"   },
        { data: 'vocTypeNm1',   className: "text-center"   },
        { data: 'rcptChnnNm',   className: "text-center"   },
        { data: 'custNm',   className: "text-center"   },
        { data: 'vocTitle',   className: "text-center"   },
        { data: 'regUserNm',   className: "text-center"   },
        { data: 'regDt',   className: "text-center"   },
        { data: 'vocStatusNm',   className: "text-center"   },
        { data: 'vocActTypeNm1',   className: "text-center"   },
        { data: 'vocActUserNm',   className: "text-center"   },
        { data: 'vocActDt',   className: "text-center"   },
    ],
    //scrollY             : '300px',
    //scrollCollapse      : true,
    scrollX: '100%',
    scrollXInner: '2300px',
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
    $('.btn-wrap .btn-add').on('click', function(){ popAddUser(); });

    // dropbox data setting ----------------
    //makeDropdownList('USE_YN', $('#registForm').find('.d-retireYn'));
    //makeDropdownList('USE_YN', $('#registForm').find('.d-useYn'));

    ajaxLoadData();

}

/**
 * 지정된 날짜의 방문자 통계 정보를 조회
 *  => chart 및 grid를 그린다.
 */
let ajaxLoadData = function(){
    let param = {};
    param.companyCd = $('#searchForm').find('#companyCd').val();
    param.userId = $('#searchForm').find('#userId').val();
    param.userNm = $('#searchForm').find('#userNm').val();

    AjaxUtil.get(
        '/kmacvoc/v1/voc/list',
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
 * 등록/수정을 위한 사용자상세팝업 오픈
 */
let popAddUser = function(){

}

/**
 * 사용자상세 팝업
 */
let popDtlUser =  function(){

}

