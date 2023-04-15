-- Function: public.countbyvoteitemid(integer)

CREATE OR REPLACE FUNCTION public.countbyvoteitemid(
    IN itemId integer
)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    RETURN (SELECT COUNT(*) FROM vote_record WHERE vote_item_id = itemId);
END;
$BODY$;

ALTER FUNCTION public.countbyvoteitemid(integer)
    OWNER TO admin;

-- Function: public.count_vote_record_by_item_id()

CREATE OR REPLACE FUNCTION public.findbyuseridandvoteitemid(
    IN userId integer,
    IN itemId integer
)
    RETURNS SETOF vote_record
    LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    RETURN QUERY SELECT * FROM public.vote_record WHERE user_id = userId AND vote_item_id = itemId;
END;
$BODY$;

ALTER FUNCTION public.findbyuseridandvoteitemid(integer, integer)
    OWNER TO admin;

-- Function: public.deletebyvoteitemid()

CREATE OR REPLACE FUNCTION deletebyvoteitemid(itemId INTEGER)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM vote_record WHERE vote_item_id = itemId;
END;
$$ LANGUAGE plpgsql;

ALTER FUNCTION public.deletebyvoteitemid(integer)
    OWNER TO admin;



-- Function: public.find_user_by_email(VARCHAR)

CREATE OR REPLACE FUNCTION public.finduserbyemail(email_in VARCHAR)
    RETURNS TABLE (id INTEGER, username VARCHAR, email VARCHAR, password VARCHAR, role varchar) AS $$
BEGIN
    RETURN QUERY SELECT u.* FROM _user u WHERE u.email = email_in;
END;
$$ LANGUAGE plpgsql;

ALTER FUNCTION public.finduserbyemail(VARCHAR)
    OWNER TO admin;


-- Function: public.find_all_valid_token_by_user(integer)

CREATE OR REPLACE FUNCTION public.findallvalidtokenbyuser(IN user_id_in INTEGER, OUT refcursor REFCURSOR)
AS $$
BEGIN
    OPEN refcursor FOR
        SELECT t.* FROM token t
                            INNER JOIN "_user" u ON t.user_id = u.id
        WHERE u.id = user_id_in AND (t.expired = false OR t.revoked = false);
END
$$ LANGUAGE plpgsql;

ALTER FUNCTION public.findallvalidtokenbyuser(integer)
    OWNER TO admin;

-- Function: public.find_token_by_token(integer)

CREATE OR REPLACE FUNCTION public.findtokenbytoken(IN token_in VARCHAR)
    RETURNS TABLE (id INTEGER, token VARCHAR, revoked BOOLEAN, expired BOOLEAN, user_id INTEGER) AS $$
BEGIN
    RETURN QUERY SELECT t.* FROM token t WHERE t.token = token_in;
END;
$$ LANGUAGE plpgsql;

ALTER FUNCTION public.findtokenbytoken(VARCHAR)
    OWNER TO admin;
