package com.group.libraryapp.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
//연관관계 주인
@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id=null;

    //user가 one
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    protected UserLoanHistory(){
    }


    public UserLoanHistory(User user, String bookName) {
        this.user=user;
        this.bookName = bookName;
        this.isReturn=false;
    }


    public void doReturn(){
        this.isReturn=true;
    }

    public String getBookName(){
        return this.bookName;
    }
}
