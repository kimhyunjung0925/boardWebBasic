package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardHeartEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardHeartDAO {
    public static int insBoardHeart(BoardHeartEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board_heart (iuser, iboard) VALUES (?, ?) ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoardHeart(BoardHeartEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board_heart WHERE iuser = ? AND iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    // 좋아요 했으면 1, 안했으면 0 리턴 |||||| iboard, iuser 값 받아와야함
    public static int selIsHeart(BoardHeartEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iuser FROM t_board_heart WHERE iuser = ? AND iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            rs = ps.executeQuery();
            if(rs.next()){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }
}

// 1.싫어요 찍히게 해보기
// 2.좋아요,싫어요 옆에 좋아요 찍힌 개수 찍어보기
// 3.댓글에 글쓴이가 쓰면 옆에 글쓴이 찍히게 해보기
