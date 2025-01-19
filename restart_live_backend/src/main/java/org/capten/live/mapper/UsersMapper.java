package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.Users;
import org.capten.live.model.UsersExample;

public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int insert(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int insertSelective(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    Users selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByExampleSelective(@Param("row") Users row, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByExample(@Param("row") Users row, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByPrimaryKeySelective(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByPrimaryKey(Users row);
}