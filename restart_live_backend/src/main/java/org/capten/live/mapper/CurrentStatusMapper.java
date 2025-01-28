package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.CurrentStatus;
import org.capten.live.model.CurrentStatusExample;

public interface CurrentStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    long countByExample(CurrentStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByExample(CurrentStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insert(CurrentStatus row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insertSelective(CurrentStatus row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    List<CurrentStatus> selectByExample(CurrentStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    CurrentStatus selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExampleSelective(@Param("row") CurrentStatus row, @Param("example") CurrentStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExample(@Param("row") CurrentStatus row, @Param("example") CurrentStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKeySelective(CurrentStatus row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.current_status
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKey(CurrentStatus row);

    List<CurrentStatus> getCurrentStatusByUsername(String userNameByToken);
}