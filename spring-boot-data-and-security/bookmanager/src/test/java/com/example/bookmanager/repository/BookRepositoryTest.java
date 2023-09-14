package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import com.example.bookmanager.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Transactional
    void bookTest(){
        Book book = new Book();
        book.setName("bookname");
        book.setAuthorId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    void bookRelationTest(){
        givenBookAndReview();

        // 일반적으론, 인증정보를 통해서 users 값을 가져온다
        Users users = usersRepository.findByEmail("sehun@naver.com");

        System.out.println("User : "+ users);
        System.out.println("Review : " + users.getReviews().get(0));
        System.out.println("Book : "+ users.getReviews().get(0).getBook());
//        System.out.println("Publisher : "+users.getReviews().get(0).getBook().getPublisher());
    }

//    @Transactional
    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("Jpa starting");

        Publisher publisher = new Publisher();
        publisher.setName("sehun Co.");

        book.setPublisher(publisher);
        bookRepository.save(book);

        // Object 은 call by reference 이기에 올바른 로직.
        // 하지만 가독성이 훼손되므로, addBook 이라는 메소드를 생성하는 것도 좋음
//        publisher.getBooks().add(book);
//        publisherRepository.save(publisher);

//        publisher.addBook(book);
//        publisherRepository.save(publisher);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());


        Book book1 = bookRepository.findAll().get(0);
        book1.getPublisher().setName("Co.");

        bookRepository.save(book1);

        System.out.println("publishers : "+publisherRepository.findAll());
    }


    private void givenBookAndReview(){
        givenReview(givenUsers(), givenBook(givenPublisher()));
    }

    private Users givenUsers(){
        Users users = new Users("sehun", "sehun@naver.com");
        usersRepository.save(users);
        return users;
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("책 이름");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("SH CO.");

        return publisherRepository.save(publisher);
    }

    private void givenReview(Users users, Book book){
        Review review = new Review();
        review.setTitle("리뷰 제목");
        review.setContent("~~~리뷰 내용~~~");
        review.setScore(5.0f);
        review.setUsers(users);
        review.setBook(book);

//        System.out.println(reviewRepository.save(review));

        reviewRepository.save(review);
    }
}