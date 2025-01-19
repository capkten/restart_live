package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.TasksComment;
import org.capten.live.model.TasksCommentExample;

public interface TasksCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    long countByExample(TasksCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int deleteByExample(TasksCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int insert(TasksComment row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int insertSelective(TasksComment row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    List<TasksComment> selectByExample(TasksCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    TasksComment selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByExampleSelective(@Param("row") TasksComment row, @Param("example") TasksCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByExample(@Param("row") TasksComment row, @Param("example") TasksCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByPrimaryKeySelective(TasksComment row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.tasks_comment
     *
     * @mbg.generated Sun Jan 19 12:56:20 CST 2025
     */
    int updateByPrimaryKey(TasksComment row);
}