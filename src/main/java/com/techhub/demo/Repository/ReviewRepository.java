package com.techhub.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.techhub.demo.Model.ProductModel;
import com.techhub.demo.Model.RegistrationModel;
import com.techhub.demo.Model.ReviewModel;

@Repository
public class ReviewRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean addReview(ReviewModel review) {
		String sql = "INSERT INTO reviews (Rid, prodid, rating, comment) VALUES (?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, review.getUserId(), review.getProductId(), review.getRating(),
				review.getComment());
		return result > 0;
	}

	public boolean updateReview(ReviewModel review) {
		int value = jdbcTemplate.update(
				"UPDATE reviews SET rating = ?, comment = ?, reviewdate = CURRENT_TIMESTAMP WHERE reviewid = ?",
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, review.getRating());
						ps.setString(2, review.getComment());
						ps.setInt(3, review.getReviewId());
					}

				}

		);
		return value > 0;
	}

	public List<ReviewModel> getReviewsByProductId(int productId) {
		return jdbcTemplate.query(
				"SELECT r.reviewid, r.prodid, r.rid, r.rating, r.comment, r.reviewdate,u.Name AS ReviewerName, p.ProdName AS ProductName "
						+ "FROM reviews r JOIN RegistrationMaster u ON r.Rid = u.Rid JOIN Product p ON r.prodid = p.ProdId "
						+ "WHERE r.prodid = ?",
				new Object[] { productId }, new RowMapper<ReviewModel>() {
					@Override
					public ReviewModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReviewModel review = new ReviewModel();
						review.setReviewId(rs.getInt("reviewid"));
						review.setProductId(rs.getInt("prodid"));
						review.setUserId(rs.getInt("rid"));
						review.setRating(rs.getInt("rating"));
						review.setComment(rs.getString("comment"));
						review.setReviewDate(rs.getString("reviewdate"));

						RegistrationModel reg = new RegistrationModel();
						reg.setName(rs.getString("ReviewerName"));

						ProductModel prod = new ProductModel();
						prod.setProname(rs.getString("ProductName"));

						review.setRegistration(reg);
						review.setProduct(prod);

						return review;
					}
				});
	}

	public boolean deleteReview(int reviewId) {
		String sql = "DELETE FROM reviews WHERE reviewid = ?";
		int rows = jdbcTemplate.update(sql, reviewId);
		return rows > 0;
	}

}
