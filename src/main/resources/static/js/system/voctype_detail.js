$(function () {
    // 초기 설정 및 수행
    init();
});

/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    // event 연결 ----------------
    $('.btn-wrap .btn-go-list').on('click', function(){ goList(); });           //목록
    $('.btn-wrap .btn-save-company').on('click', function(){ saveData(); });    //저장
    $('.btn-wrap .btn-delt-company').on('click', function(){ deleteData(); });  //삭제
    $('.btn-wrap .btn-check-company').on('click', function(){ checkCompanyCode(); }); //중복체크
    $('.btn-wrap .btn-cancel-file').on('click', function(){ cancelFile(); });     //파일취소

    $('#registForm').find('#companyCd').on('change', function(){ $('#registForm').find('#dupChkYn').val(''); }); //회사코드 변경시 중복확인 초기화

    // dropbox data setting ----------------
    DropdownUtil.makeCodeList('COM999','USE_YN', $('#registForm').find('.d-useYn'));

    setTimeout(function() {
        let companySeq = localStorage.getItem("companySeq");
        if(companySeq > 0) searchData(companySeq);
        localStorage.removeItem('companySeq');
    }, 200);
}


/**
 * 목록화면 이동
 */
let goList = function(){
    goPage('/system/companylist');
}

/**
 * 중복체크
 */
let checkCompanyCode = function(){
    let companyCd = $('#registForm').find('#companyCd').val();
    if(ObjectUtil.isEmpty(companyCd)) {
        alert('회사코드를 입력해 주세요.');
        $('#registForm').find('#companyCd').focus();
        return;
    }

    let param = {'companyCdForCheck': companyCd};
    $('#registForm').find('#dupChkYn').val('N');    //중복체크값 초기화

    AjaxUtil.get(
        '/kmacvoc/v1/company/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                if(result.data.list.length == 0) {
                    $('#registForm').find('#dupChkYn').val('Y');
                    alert('사용가능한 회사코드입니다.');
                } else {
                    alert('회사코드가 중복됩니다.');
                }
            }
        }
    );
}

/**
 * 데이터 조회
 */
let searchData =  function(key){
    AjaxUtil.get(
        '/kmacvoc/v1/company/'+key,
        {},
        function(result){
            if(result && result.data){
                let d = result.data;
                d.useStartDt = Util.dateFormat(d.useStartDt,'-');
                d.useEndDt = Util.dateFormat(d.useEndDt,'-');
                $('#registForm').form('clear');
                $('#registForm').form('set.values', d);
                $('#registForm').find('#companyLogoFile').html(d.companyLogoFileNm);
                $('#registForm').find('#companyLogoFile').attr('href', '/kmacvoc/v1/company/download?companyLogoFileNm='+d.companyLogoFileNm+'&companyLogoPath='+encodeURI( d.companyLogoPath));
                $('#registForm').find('#dupChkYn').val('Y');
                //$('#registForm').find('#companyLogoPath').removeClass('blind');

                $('.btn-delt-company').removeClass('blind');
            }
        }
    );
}

/**
 * 데이터 저장
 */
let saveData = function(){
    let $frm = $('#registForm');
    if($frm.find('#companyCd').val() == '') {
        alert('회사코드는 필수항목입니다.');
        $frm.find('#companyCd').focus();
        return;
    }
    if($frm.find('#dupChkYn').val() != 'Y') {
        alert('회사코드의 중복확인은 필수입니다.'); return;
    }
    if($frm.find('#companyNm').val() == '') {
        alert('회사명(국문)은 필수항목입니다.');
        $frm.find('#companyNm').focus();
        return;
    }
    if($frm.find('#useStartDt').val() > $frm.find('#useEndDt').val()) {
        alert('사용종료일은 사용시작일 이후로 입력해 주십시오.');
        $frm.find('#useEndDt').focus();
        return;
    }

    let form = $("#registForm")[0];
    let formData = new FormData(form);
    let companySeq = $frm.find('#companySeq').val();
    let url = companySeq == '0' ? '/kmacvoc/v1/company/add' : '/kmacvoc/v1/company/modify';

    $.ajax({
        method: "POST",
        url: url,
        data: formData,
        contentType: false,
        processData: false
    })
    .done(function(result) {
        if(result && result.messageCode == '0000'){
            alert(result.data.rtnMessage);
            goList();
        }
    });
}

/**
 * 데이터 삭제
 */
let deleteData = function(){

    if(!confirm('회사정보를 삭제하시겠습니까?')) return;

    let companySeq = $('#registForm').find('#companySeq').val();
    let url = '/kmacvoc/v1/company/remove/'+companySeq;

    AjaxUtil.post(
        url,
        {},
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                goList();
            }
        }
    );
}

/**
 * 파일 선택 취소
 */
let cancelFile = function(){
    $('#registForm').find('#logoFile').val('');
}
