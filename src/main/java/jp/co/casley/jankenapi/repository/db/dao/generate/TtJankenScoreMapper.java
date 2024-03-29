package jp.co.casley.jankenapi.repository.db.dao.generate;

import java.util.List;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScore;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScoreExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TtJankenScoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    long countByExample(TtJankenScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByExample(TtJankenScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByPrimaryKey(Integer scoreNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insert(TtJankenScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insertSelective(TtJankenScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<TtJankenScore> selectByExampleWithRowbounds(TtJankenScoreExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<TtJankenScore> selectByExample(TtJankenScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    TtJankenScore selectByPrimaryKey(Integer scoreNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExampleSelective(@Param("record") TtJankenScore record, @Param("example") TtJankenScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExample(@Param("record") TtJankenScore record, @Param("example") TtJankenScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKeySelective(TtJankenScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.tt_janken_score
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKey(TtJankenScore record);
}