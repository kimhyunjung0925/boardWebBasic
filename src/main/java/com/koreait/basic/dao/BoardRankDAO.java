package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {

    //()안에 비워둔 이유는 값을 받아오지 않는다, ?를 쓰지 않는다는 뜻..?
    public static List<BoardVO> selBoardHitsRankList(){ // 조회수
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //hit > 0 대신 NOT IN ('hit=0')도 되나..?
        String sql = " SELECT A.iboard, A.title, A.writer, A.hit, A.rdt, B.nm AS writerNm "
                    + " FROM t_board A INNER JOIN t_user B ON A.writer = B.iuser "
                    + " WHERE A.hit > 0 ORDER BY A.hit DESC, A.iboard DESC LIMIT 10 ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .cnt(rs.getInt("hit"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }

    public static List<BoardVO> selBoardCmtRankList() { //댓글 수
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, C.cnt " +
                     " FROM t_board A INNER JOIN t_user B ON A.writer = B.iuser " +
                    " INNER JOIN ( SELECT iboard, COUNT(icmt) AS cnt FROM t_board_cmt GROUP BY iboard ) C " +
                    " ON A.iboard = C.iboard " +
                    " ORDER BY C.cnt DESC LIMIT 10 " ;
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .cnt(rs.getInt("cnt"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static List<BoardVO> selBoardHeartRankList() { //좋아요
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm, C.cnt " +
                " FROM t_board A INNER JOIN t_user B ON A.writer = B.iuser " +
                " INNER JOIN ( SELECT iboard, COUNT(iuser) AS cnt FROM t_board_heart GROUP BY iboard ) C " +
                " ON A.iboard = C.iboard " +
                " ORDER BY C.cnt DESC LIMIT 10 " ;
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .cnt(rs.getInt("cnt"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

}
