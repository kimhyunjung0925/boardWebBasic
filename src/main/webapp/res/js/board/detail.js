function isDelCmt(iboard, icmt) {
    console.log(icmt);

    if(confirm('댓글을 삭제하시겠습니까?')) {
        location.href = '/board/cmt/del?iboard=' + iboard + '&icmt=' + icmt;
    }
}
var cmtModContainerElem = document.querySelector('.cmtModContainer')

//수정창뜨면 수정창에서 취소 누르기
var btnCancelElem = cmtModContainerElem.querySelector('#btnCancel');
btnCancelElem.addEventListener('click',function() {
   cmtModContainerElem.style.display = 'none';
});

// 수정창 키고
function openModForm(icmt, ctnt) {
        cmtModContainerElem.style.display = 'flex';
        var cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm');
        cmtModFrmElem.icmt.value = icmt;
        cmtModFrmElem.ctnt.value = ctnt;
}

