package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandService();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        String jsonString = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }


    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        String id = JSON.parseObject(s, String.class);
        brandService.deleteById(Integer.valueOf(id));
        response.getWriter().write("success");

    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        String id = JSON.parseObject(s, String.class);
        Brand brand = brandService.selectById(Integer.valueOf(id));
        String jsonString = JSON.toJSONString(brand);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.update(brand);
        response.getWriter().write("success");
    }

    public void delByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int[] ids = JSON.parseObject(s, int[].class);
        brandService.delByIds(ids);
        response.getWriter().write("success");

    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
