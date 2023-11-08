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
    scrollY             : '250px',
    //scrollCollapse      : true,
    scrollX: '100%',
    scrollXInner: '1800px',
};

$(function () {
    // 초기 설정 및 수행
    init();
});

/**
 * 초기 설정 및 수행 내용
 */
let init = function(){

    // event 연결 ----------------
    //$('.btn-search').on('click', function(){ ajaxLoadData(); });
    //$('.btn-wrap .btn-add').on('click', function(){ goAddCust(); });

    // dropbox data setting ----------------
    //makeDropdownList('COM_999','USE_YN', $('#registForm').find('.d-useYn'));

    ajaxLoadData();
}

/**
 * 정보 조회 => grid를 그린다.
 */
let ajaxLoadData = function(){
    let param = {'custSeq':$('#registForm').find('custSeq').val()};

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
        $Grid = gridUtil.loadGrid("listDataTableCustVoc", gridOptions);
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