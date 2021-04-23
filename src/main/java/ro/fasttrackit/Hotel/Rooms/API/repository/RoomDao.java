package ro.fasttrackit.Hotel.Rooms.API.repository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomFilters;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Room;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomPage;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Room> getAllWithFilters(RoomPage roomPage, RoomFilters roomFilters) {


        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> root = criteriaQuery.from(Room.class);

        Predicate predicate = getPredicate(roomFilters, root);
        criteriaQuery.where(predicate);
        setOrder(roomPage, criteriaQuery, root);

        TypedQuery<Room> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(roomPage.getPageNumber() * roomPage.getPageSize());
        typedQuery.setMaxResults(roomPage.getPageSize());

        Pageable pageable = getPageable(roomPage);

        long roomCount = getRoomCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, roomCount);

    }

    private long getRoomCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Room> countRoot = countQuery.from(Room.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(RoomPage roomPage) {
        Sort sort = Sort.by(roomPage.getSortDirection(), roomPage.getSortBy());
        return PageRequest.of(roomPage.getPageNumber(), roomPage.getPageSize(), sort);
    }

    private void setOrder(RoomPage roomPage, CriteriaQuery<Room> criteriaQuery, Root<Room> root) {
        if (roomPage.getSortDirection().equals((Sort.Direction.ASC))) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(roomPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(roomPage.getSortBy())));
        }
    }

    private Predicate getPredicate(RoomFilters roomFilters, Root<Room> root) {
        List<Predicate> whereClause = new ArrayList<>();
        Optional.ofNullable(roomFilters.getEtaj())
                .ifPresent((element -> whereClause.add(criteriaBuilder.equal(root.get("etaj"), element))));
        Optional.ofNullable(roomFilters.getHotelName())
                .ifPresent((element -> whereClause.add(criteriaBuilder.equal(root.get("hotelName"), element))));
        Optional.ofNullable(roomFilters.getNumber())
                .ifPresent((element -> whereClause.add(criteriaBuilder.equal(root.get("number"), element))));
        Optional.ofNullable(roomFilters.getTv())
                .ifPresent((element -> whereClause.add(criteriaBuilder.isTrue(root.get("roomFacilities").get("tv")))));
        Optional.ofNullable(roomFilters.getDoubleBed())
                .ifPresent((element -> whereClause.add(criteriaBuilder.isTrue(root.get("roomFacilities").get("doubleBed")))));
        return criteriaBuilder.and(whereClause.toArray(new Predicate[0]));

    }
}
