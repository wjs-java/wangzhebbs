package com.bbs.domain;

/**
 * 敏感词
 */
public class Word {
    private Integer wordId;
    private String work;//敏感词
    private Integer status;//是否启用

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", work='" + work + '\'' +
                ", status=" + status +
                '}';
    }
}
