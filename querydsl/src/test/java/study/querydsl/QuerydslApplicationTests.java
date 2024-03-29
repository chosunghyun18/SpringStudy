package study.querydsl;

import static org.assertj.core.api.Assertions.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.helloworld.model.Hello;
import study.querydsl.domain.helloworld.model.QHello;


@SpringBootTest
@Transactional
@Commit // commit 이 없이 transactional 만 있으면 rollbaack 됨
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
	}
	@Test
	void contextHelloLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);

		QHello qHello = new QHello("hi"); // no space in variable

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();
		assertThat(result).isEqualTo(hello);
		// test lombok
		assertThat(result.getId()).isEqualTo(hello.getId());

	}

}
