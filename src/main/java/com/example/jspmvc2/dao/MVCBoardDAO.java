package com.example.jspmvc2.dao;

import com.example.jspmvc2.dto.MVCBoardDTO;
import com.example.jspmvc2.mybatis.factory.MyBatisSessionFactory;
import com.example.jspmvc2.mybatis.mapper.MVCBoardMapper;
import com.example.jspmvc2.util.Encrypt;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MVCBoardDAO {
    public int selectCount(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.selectCount(map);
        System.out.println("selectCount - 행 개수 = " + result);
        sqlSession.close();
        return result;
    }

    public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        List<MVCBoardDTO> result = mapper.selectListPage(map);
        sqlSession.close();
        return result;
    }

    public int insertWrite(MVCBoardDTO dto) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.insertWrite(dto);
        if (result == 1) {
            sqlSession.commit();
            System.out.println("새로운 mvcboard 저장 성공");
        } else {
            System.out.println("새로운 mvcboard 저장 실패");
        }
        sqlSession.close();
        return result;
    }

    public List<MVCBoardDTO> selectListPageWithPaging(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        List<MVCBoardDTO> result = mapper.selectListPageWithPaging(map);
        sqlSession.close();
        return result;
    }

    public MVCBoardDTO selectView(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        MVCBoardDTO dto = mapper.selectView(idx);
        sqlSession.close();
        return dto;
    }

    public void updateVisitCount(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.updateVisitCount(idx);
        System.out.println("update query result val = " + result);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("조회수 증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public void downCoundPlus(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.downCountPlus(idx);
        System.out.println("downCountPlus - update query result val = " + result);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("다운로드 횟수 1증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public boolean confirmPassword(String pass, String idx) {
        Map<String, String> map = new HashMap<>();
        map.put("pass", Encrypt.getEncrypt(pass));
        map.put("idx", idx);
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.confirmPassword(map);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int deletePost(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.deletePost(idx);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("board 삭제 중 오류 발생...");
        }
        return result;
    }

    public int updatePost(MVCBoardDTO dto) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.updatePost(dto);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("board update 중 오류 발생...");
        }
        sqlSession.commit();
        return result;
    }
}

