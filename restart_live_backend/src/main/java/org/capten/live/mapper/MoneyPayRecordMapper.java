package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.MoneyPayRecord;
import org.capten.live.model.MoneyPayRecordExample;

public interface MoneyPayRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    long countByExample(MoneyPayRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByExample(MoneyPayRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insert(MoneyPayRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insertSelective(MoneyPayRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    List<MoneyPayRecord> selectByExample(MoneyPayRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    MoneyPayRecord selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExampleSelective(@Param("row") MoneyPayRecord row, @Param("example") MoneyPayRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExample(@Param("row") MoneyPayRecord row, @Param("example") MoneyPayRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKeySelective(MoneyPayRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.money_pay_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKey(MoneyPayRecord row);
}