package jp.co.casley.jankenapi.repository.db.dao.generate;

import java.util.List;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategyUser;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategyUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtJankenStrategyUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    long countByExample(MtJankenStrategyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByExample(MtJankenStrategyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insert(MtJankenStrategyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insertSelective(MtJankenStrategyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenStrategyUser> selectByExampleWithRowbounds(MtJankenStrategyUserExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenStrategyUser> selectByExample(MtJankenStrategyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    MtJankenStrategyUser selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExampleSelective(@Param("record") MtJankenStrategyUser record, @Param("example") MtJankenStrategyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExample(@Param("record") MtJankenStrategyUser record, @Param("example") MtJankenStrategyUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKeySelective(MtJankenStrategyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_strategy_user
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKey(MtJankenStrategyUser record);
}