package jp.co.casley.jankenapi.repository.db.dao.generate;

import java.util.List;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategy;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtJankenStrategyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    long countByExample(MtJankenStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByExample(MtJankenStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByPrimaryKey(String strategy);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insert(MtJankenStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insertSelective(MtJankenStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenStrategy> selectByExampleWithRowbounds(MtJankenStrategyExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenStrategy> selectByExample(MtJankenStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    MtJankenStrategy selectByPrimaryKey(String strategy);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExampleSelective(@Param("record") MtJankenStrategy record, @Param("example") MtJankenStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExample(@Param("record") MtJankenStrategy record, @Param("example") MtJankenStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKeySelective(MtJankenStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKey(MtJankenStrategy record);
}