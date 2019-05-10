package com.file.management.pojo.state;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_state")
public class State {

    @Id
    @Column(name ="NAME")
    private String name;
    @Column(name ="SOURCE")
    private String source;
    @Column(name = "EXP")
    private String exp;
    @Column(name = "MIN")
    private String  min;
    @Column(name = "MAX")
    private String  max;
    @Column(name = "NUM")
    private String  num;
    @Column(name = "LESS")
    private String  less;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String explain) {
        this.exp = explain;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLess() {
        return less;
    }

    public void setLess(String less) {
        this.less = less;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    @Column(name = "FIT")
    private String  fit;
    @Column(name = "MORE")
    private String  more;
    @Column(name = "BOOL")
    private String  bool;




}
