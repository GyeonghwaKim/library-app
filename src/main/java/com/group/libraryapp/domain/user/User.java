package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;

    @Column(nullable=false,length = 25, name="name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories=new ArrayList<>();
//엔티티는 매개변수하나도 없는 생성자 필요
    protected User() {
    }

    public User(String name, Integer age) {

        if(name==null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다",name));
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name=name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this,bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory target=this.userLoanHistories.stream()
                .filter(history->history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        target.doReturn();

    }
}
