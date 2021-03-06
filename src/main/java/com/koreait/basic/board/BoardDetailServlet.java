package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardHeartEntity;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int nohits = Utils.getParameterInt(req,"nohits");
        int iboard = Utils.getParameterInt(req,"iboard");

        BoardDTO param = new BoardDTO();
        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);
        req.setAttribute("data", data);

        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIboard(iboard);
        req.setAttribute("cmtList", BoardCmtDAO.selBoardCmtList(cmtParam));

        int loginUserPk = Utils.getLoginUserPk(req);
        if(loginUserPk > 0) { //로그인이 되어있어야 하고 로그인 되어있을 때 좋아요 했는지 체크
            BoardHeartEntity bhparam = new BoardHeartEntity();
            bhparam.setIuser(loginUserPk);
            bhparam.setIboard(iboard);
            req.setAttribute("isHeart", BoardHeartDAO.selIsHeart(bhparam));
        }

        if(loginUserPk != data.getWriter() && nohits != 1) {
            BoardDAO.updBoardHitUp(param);
        }

        Utils.displayView(data.getTitle(), "board/detail",req,res);

    }

}
