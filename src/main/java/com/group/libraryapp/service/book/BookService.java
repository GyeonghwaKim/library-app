package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    //책 저장소
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        //책정보 가저온다 -> 대출 중인지 확인한다 -> 대출중이라면 예외 발생
        Book book=bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("대출된 책 입니다");
        }

        User user=userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        user.loanBook(book.getName());


        //userLoanHistoryRepository.save(new UserLoanHistory(user,book.getName()));

    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        //유저이름으로 유저 찾기
    User user=userRepository.findByName(request.getUserName())
            .orElseThrow(IllegalArgumentException::new);

    user.returnBook(request.getBookName());
    }

}
