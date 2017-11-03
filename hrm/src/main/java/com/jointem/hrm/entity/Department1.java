package com.jointem.hrm.entity;

/**
 * @Author :zhanglu
 * @Description:
 * @Date :Created in 10:21 2017/9/22
 * @Modified By:
 */
public class Department1 {
    private int id;
    private String name;
    private int pId;
    private int isParent;

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(isParent == 1){
            this.id = id;
        }else{
            this.id = id-100000000;
        }

    }


}
