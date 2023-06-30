package com.github.albertloubet.libraryfx.repository;

import com.github.albertloubet.libraryfx.enumerator.ProfileEnum;
import com.github.albertloubet.libraryfx.exception.EntityNotFoundException;
import com.github.albertloubet.libraryfx.exception.RepositoryException;
import com.github.albertloubet.libraryfx.exception.UserNotFoundException;
import com.github.albertloubet.libraryfx.foundation.RepositoryFoundation;
import com.github.albertloubet.libraryfx.model.entity.User;

import java.sql.SQLException;

public class UserRepository extends RepositoryFoundation {

    public User findById(Long id) {
        try (var conn = getConnection();
             var st = conn.createStatement();
             var rs = st.executeQuery(String.format("SELECT * FROM usersys WHERE idt_user = %s", id.toString()))) {
            if (rs.next()) {
                var user = User.builder()
                        .username(rs.getString("des_username"))
                        .profile(ProfileEnum.valueOf(rs.getString("des_profile")))
                        .name(rs.getString("des_name"))
                        .build();
                user.setId(id);
                user.setCreatedAt(rs.getTimestamp("dat_created_at").toLocalDateTime());
                user.setUpdatedAt(rs.getTimestamp("dat_updated_at").toLocalDateTime());
                return user;
            }
            throw new EntityNotFoundException(id);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public User findUser(String username, String password) {
        try (var conn = getConnection();
             var st = conn.createStatement();
             var rs = st.executeQuery(String.format("""
                     SELECT * FROM usersys WHERE des_username = '%s' AND des_password = '%s'
                     """, username, password))) {
            if (rs.next()) {
                var user = User.builder()
                        .username(rs.getString("des_username"))
                        .profile(ProfileEnum.valueOf(rs.getString("des_profile")))
                        .name(rs.getString("des_name"))
                        .build();
                user.setId(rs.getLong("idt_user"));
                user.setCreatedAt(rs.getTimestamp("dat_created_at").toLocalDateTime());
                user.setUpdatedAt(rs.getTimestamp("dat_updated_at").toLocalDateTime());
                return user;
            }
            throw new UserNotFoundException();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void create(User user) {
        var query = """
                INSERT INTO usersys(
                    des_name,
                    des_username,
                    des_password,
                    des_profile
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?
                )
                """;
        try (var conn = getConnection();
             var pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getProfile().name());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void update(User user) {
        var query = """
                UPDATE usersys SET des_name = ?, des_username = ? WHERE idt_user = ?
                """;
        try (var conn = getConnection();
             var pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setLong(3, user.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void changePassword(Long userId, String newPassword) {
        var query = """
                UPDATE usersys SET des_password = ? WHERE idt_user = ?
                """;
        try (var conn = getConnection();
             var pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newPassword);
            pstmt.setLong(2, userId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
