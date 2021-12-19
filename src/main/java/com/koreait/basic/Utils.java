package com.koreait.basic;

import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Utils {
    public static void displayView(String title, String page, HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        req.setAttribute("title", title);
        req.setAttribute("page", page);
        Utils.disForward(req, res,"layout");
    }

    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/" + jsp + ".jsp").forward(req,res);
    }


    public static int getParameterInt(HttpServletRequest req, String key) {
        String val = req.getParameter(key);
        return parseStringToInt(val,0);
    }

    public static int getParameterInt(HttpServletRequest req, String key, int defVal) {
        return parseStringToInt(req.getParameter(key),defVal);
    }

    public static int parseStringToInt(String str, int defVal){
        try{
            return Integer.parseInt(str);
        } catch (Exception e) {}
        return defVal;
    }

    public static UserEntity getLoginUser(HttpServletRequest req){
        HttpSession session = req.getSession();
        return (UserEntity) session.getAttribute("loginUser");
    }

    public static int getLoginUserPk(HttpServletRequest req) {
        UserEntity loginUSer = getLoginUser(req);
        return loginUSer == null ? 0 : loginUSer.getIuser();
    }
}
