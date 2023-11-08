let gridUtil = {
    /**
     * Grid(DataTables) 생성 및 loading
     *
     * @param gridTableId Grid 대상이 되는 table 객체의 id
     * @param reqGridOptions Grid 요청 옵션
     *
     * @return grid  객체
     *
     */
    loadGrid : function(gridTableId, reqGridOptions){
        // grid 객체
        let $gridObj = null;

        if(typeof(gridTableId) == 'undefined') return ;

        let $gridTableObj = $("#"+gridTableId);
        if(!$gridTableObj.length) return;

        // 기본 option
        let defaultGridOptions = {
            responsive  : true,
            lengthChange: true,
            autoWidth   : true,
            paging      : false,
            searching   : false,
            ordering    : false,
            info        : false,    //'Showing 1 to 3 of 3 entries' 문자열 없애기 (=false)
            language    : {
                emptyTable      : "데이터가 없습니다.",
                zeroRecords     : "No matching records found",
                loadingRecords  : "Loading...",
                thousands       : ",",
            },
            //buttons             : ["copy", "csv", "print"],
        }

        // [Start] 옵션 구성 ---------------------
        reqGridOptions = (reqGridOptions)? reqGridOptions : {};
        let gridOptions = $.extend(true, {}, defaultGridOptions,reqGridOptions);

        // footer에서 합계나 평균 처리할 옵션처리내용
        let footerCalculationOpt = gridOptions.footerCalculation;
        // 예시)
        //  footerCalculation : {
        //      targetColIndexs : [1,2,3,4,5,6,7,8,9,10,11],
        //      rowCalcTypes    : ["sum", "average"],
        //      isNumberFormat  : true,
        //  },
        if(typeof(gridOptions.footerCallback) != 'function' && footerCalculationOpt != undefined && footerCalculationOpt != null){

            // footer의 각 cell의 class를 첫번 째 row와 동기화 (다중 row일 때 첫번째row에만 적용되는 문제 때문)
            let fn_foooterStyleSynch = function(api){
                // footer가 있을 때
                let $footerRows = $(api.table().footer()).find("tr");
                if($footerRows.length ){
                    let firstCellClassArry = [];
                    $footerRows.each(function(rowIdx, row){
                        // cell 단위 loop
                        $(row).find("th").each(function(cellIdx, cell){
                            // 첫번 째 row의 각 cell의 class 값을 저장
                            if(rowIdx == 0) firstCellClassArry.push($(cell).attr("class"));
                            // 저장된 각 cell의 class 내용을 2번째 row의 cell 들에 적용
                            else $(cell).attr("class", firstCellClassArry[cellIdx]);
                        });
                    });
                }
            };

            // footer의 계산 항목 표시 설정 함수
            let fn_footerCalculationProcess = function ( api, row, data, start, end, display ) {
                // Remove the formatting to get integer data for summation
                var intVal = function ( i ) {
                    return typeof i === 'string' ?
                        i.replace(/[\$,]/g, '')*1 :
                        typeof i === 'number' ?
                            i : 0;
                };
                // 대상 column index 배열 (합계 또는 평균의 계산 표시 대상 column의 index 배열)
                //var coltosumIdxs    = [1,2,3,4,5,6,7,8,9,10,11];
                var coltosumIdxs    = footerCalculationOpt.targetColIndexs;
                //var rowCalcTypes    = ["sum", "average"];
                var rowCalcTypes    = footerCalculationOpt.rowCalcTypes;
                // footer가 있을 때
                let $footerRows = $(api.table().footer()).find("tr");

                var enableRowCount  = 0;
                if($footerRows.length ){
                    enableRowCount  = (rowCalcTypes.length <= $footerRows.length)? rowCalcTypes.length : $footerRows.length;
                }

                // 계산 내용 처리
                // -- 현재는 합계(sum), 평균(average) 만 처리함.
                if(enableRowCount > 0){

                    for (let colIdx in coltosumIdxs){
                        // ---- 합계 계산
                        let total     = api.column( coltosumIdxs[colIdx] ).data().reduce( function (a, b) {return intVal(a) + intVal(b);}, 0 );
                        // Total over this page
                        let pageTotal = api.column( coltosumIdxs[colIdx], { page: 'current'} ).data().reduce( function (a, b) {return intVal(a) + intVal(b);}, 0 );
                        // Update footer
                        //$( api.column( coltosumIdxs[colIdx] ).footer() ).html(pageTotal+'('+total+')');
                        // ---- 평균 계산
                        let avg = ( (end != null && end != 0)? pageTotal / end : 0 ).toFixed(1);

                        // Footer에 값 적용
                        for(let rowIdx = 0 ; rowIdx<enableRowCount ; rowIdx++){
                            let $row = $footerRows.eq(rowIdx);

                            let viewData = "";
                                 if(rowCalcTypes[rowIdx] == 'sum')     viewData = pageTotal;    // 합계
                            else if(rowCalcTypes[rowIdx] == 'average') viewData = avg;          // 평균

                            let intColEqIdx = parseInt(colIdx)+1;

                            // Update footer : numberFormat 적용
                            let realViewData = (!ObjectUtil.isEmpty(footerCalculationOpt.isNumberFormat) && footerCalculationOpt.isNumberFormat)? Util.numberFormat(viewData) : viewData;
                            let $cellObj = $row.find("th");
                            $cellObj.eq(intColEqIdx).html( realViewData );
                        }
                    }
                }
            }   // end of 'fn_footerCalculationProcess(...)'

            //---------- footerCallback 설정 start
            gridOptions.footerCallback = function (row, data, start, end, display ) {
                var api = this.api();

                // footer 항목의 스타일 설정
                fn_foooterStyleSynch(api);

                // 계산 항목 처리
                fn_footerCalculationProcess(api, row, data, start, end, display);
            }   // end of 'gridOptions.footerCallback' callback 정의
            //---------- footerCallback 설정 end
        }
        // [End] 옵션 구성 ---------------------

        //------
        gridOptions.drawCallback = function(){

        }

        //---- ** grid 실제 생성
        $gridObj = $gridTableObj.DataTable(gridOptions);

        //---- button 구성 관련
        let buttonOpt = gridOptions.buttons;
        if(buttonOpt != undefined && buttonOpt != null && typeof(buttonOpt) == "object" && buttonOpt.length > 0 ){
            setTimeout(()=> {
                $gridObj.buttons().container().appendTo('#'+gridTableId+'_wrapper .col-md-6:eq(0)');
            }, 200);
        }

        return $gridObj;
    }
}