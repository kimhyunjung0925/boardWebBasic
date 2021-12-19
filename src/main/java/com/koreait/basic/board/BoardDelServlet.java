package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req,"iboard");

        //안해도 됨
        if(Utils.getLoginUser(req) == null) {
            res.sendRedirect("/board/detail?iboard=" + iboard);
            return;
        }

        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        entity.setWriter(Utils.getLoginUserPk(req));

        int result = BoardDAO.delBoard(entity);

        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                break;
            default:
                req.setAttribute("err","글 삭제를 실패하였습니다.");
                req.getRequestDispatcher("/board/detail?iboard=" + iboard).forward(req,res);
                return;
        }

    }

}



/*
err메시지를 디테일에서 할거면 = res.sendRedirect (get방식)
err메시지를 여기서 박아서 할거면 = req.getRequestDispatcher().forward(req,res) = req,res 그대로 살려서 보냄,
                                                                (post에서 쓰면 post, get에서 쓰면 get)
 */
