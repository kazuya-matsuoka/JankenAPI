package jp.co.casley.jankenapi.repository.db.dao.generate;

import java.util.List;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenHand;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenHandExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtJankenHandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    long countByExample(MtJankenHandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByExample(MtJankenHandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int deleteByPrimaryKey(String hand);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insert(MtJankenHand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int insertSelective(MtJankenHand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenHand> selectByExampleWithRowbounds(MtJankenHandExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    List<MtJankenHand> selectByExample(MtJankenHandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    MtJankenHand selectByPrimaryKey(String hand);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExampleSelective(@Param("record") MtJankenHand record, @Param("example") MtJankenHandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByExample(@Param("record") MtJankenHand record, @Param("example") MtJankenHandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKeySelective(MtJankenHand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jankendb.mt_janken_hand
     *
     * @mbg.generated Fri Nov 01 00:32:53 JST 2019
     */
    int updateByPrimaryKey(MtJankenHand record);
}