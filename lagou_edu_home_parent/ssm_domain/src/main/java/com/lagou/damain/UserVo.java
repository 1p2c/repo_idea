package com.lagou.damain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserVo {

    private Integer currentPage;
    private Integer pageSize;
    //多条件查询 手机号 注册起始事件 结束事件
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private List <Integer> roleIdList;
    private Integer userId;

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", username='" + username + '\'' +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
