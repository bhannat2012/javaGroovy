import com.Account
import com.Service
import com.service.AccountService
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * User: Akhil Shastri
 * Date: 10/18/13
 * Time: 5:00 PM
 */
@ContextConfiguration("classpath:applicationContext.xml")
class AccountSpec extends Specification {

    Service srvc = Mock();

    @Autowired(required = true)
    Account account


    @Autowired(required = true)
    ApplicationContext ctx

    def "ben test"(){
        //expect: where:
        //when: then:

        when:
            def ac = account

        then:
            ac.id == 1
            ac.name == 'akhil'
    }

    def "context test"(){
        when:
          def  acBin = ctx.getBean("prototypeAccount");
        then:
        acBin.id == 1
        acBin.name == 'akhil'

    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    def "check database"(){
        when:
            def sql = "select 1"
            def intOut = jdbcTemplate.queryForObject(sql,Integer.class);
        then:
          intOut==1
    }


    def "check sql"(){
        when: "declare sql query"
        def sql = "select * from x"
        and: "execute it on jdbcTemplate"
        def intOut = jdbcTemplate.queryForObject(sql,Integer.class);
        then:  "should throe sql exception"
        thrown(BadSqlGrammarException)
    }

    @Shared sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")

    @Unroll
    def "unroll test form Max of #a and #b is #c"(){
         expect:
          Math.max(a,b) == c
         where:
             a|b||c
             1|2||2
             3|1||3
             1|4||4

    }

//    def "db test with sql"(){
//        when:
//           def x= sql.firstRow("select count(1) from accounts")
//        //println x
//        then:
//          x ==[1:1]
//    }

    def mockMethods(){
        setup:
         srvc.save() >> "Ok"
        when:
         def retval = srvc.save();
        then:
          retval =="Ok"
    }
}
