-- Function: public.count_by_vote_item_id(integer)

CREATE OR REPLACE FUNCTION public.count_by_vote_item_id(
    IN itemId integer
)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    RETURN (SELECT COUNT(*) FROM vote_record WHERE vote_item_id = itemId);
END;
$$;

-- Function: public.find_by_user_id_and_vote_item_id()

CREATE OR REPLACE FUNCTION public.find_by_user_id_and_vote_item_id(
    IN userId integer,
    IN voteItemId integer
)
    RETURNS SETOF vote_record
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    RETURN QUERY SELECT * FROM vote_record WHERE user_id = userId AND vote_item_id = voteItemId;
END;
$$;

-- Function: public.delete_by_vote_item_id(INTEGER)

CREATE OR REPLACE FUNCTION delete_by_vote_item_id(
    IN voteItemId INTEGER
)
    RETURNS VOID
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE FROM vote_record WHERE vote_item_id = voteItemId;
END;
$$;

-- Function: public.find_user_by_email(VARCHAR)

CREATE OR REPLACE FUNCTION public.find_user_by_email(
    IN email_in VARCHAR
)
    RETURNS SETOF _user
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    RETURN QUERY SELECT * FROM _user u WHERE u.email = email_in;
END;
$$;


-- Function: public.find_all_valid_token_by_user(integer)

CREATE OR REPLACE FUNCTION public.find_all_valid_token_by_user(
    IN user_id_in INTEGER
)
    RETURNS SETOF token
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    RETURN QUERY SELECT *
                 FROM token t
                          INNER JOIN "_user" u ON t.user_id = u.id
                 WHERE u.id = user_id_in
                   AND (t.expired = false OR t.revoked = false);
END
$$;


-- Function: public.find_token_by_token(VARCHAR)

CREATE OR REPLACE FUNCTION public.find_token_by_token(
    IN token_in VARCHAR
)
    RETURNS SETOF token
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM token t WHERE t.token = token_in;
END;
$$;

