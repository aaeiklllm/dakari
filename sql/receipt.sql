PGDMP     
    9                {            shopmedb    15.2    15.2 	    1           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            2           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            3           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            4           1262    16693    shopmedb    DATABASE     �   CREATE DATABASE shopmedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';
    DROP DATABASE shopmedb;
                postgres    false            �            1259    25707    receipt    TABLE     �   CREATE TABLE "shopping-cart".receipt (
    receipt_id integer NOT NULL,
    username character varying(64),
    details character varying(1024),
    "timestamp" timestamp without time zone
);
 $   DROP TABLE "shopping-cart".receipt;
       shopping-cart         heap    postgres    false            �            1259    25706    receipt_receiptId_seq    SEQUENCE     �   ALTER TABLE "shopping-cart".receipt ALTER COLUMN receipt_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "shopping-cart"."receipt_receiptId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            shopping-cart          postgres    false    220            .          0    25707    receipt 
   TABLE DATA                 shopping-cart          postgres    false    220   	       5           0    0    receipt_receiptId_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('"shopping-cart"."receipt_receiptId_seq"', 84, true);
          shopping-cart          postgres    false    219            �           2606    25713    receipt receipt_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "shopping-cart".receipt
    ADD CONSTRAINT receipt_pkey PRIMARY KEY (receipt_id);
 G   ALTER TABLE ONLY "shopping-cart".receipt DROP CONSTRAINT receipt_pkey;
       shopping-cart            postgres    false    220            .   �  x��W]k�0}ϯyI��ﯕ��B1l)M��=-���[}迟�e�-iH����FW�u��s�t4��l<��j�k������-C�n��:����_��m�ʕ-\,\�>���|�`�u�\^�&��c6� �o���3�������{
N$냞����yы�h��`�n������r�W����G�W�v|�{��{;��e��'�s[�98���̜��,���-=�|>�ץn7�|n\hh��&��� ��%$��{��;ًA�4�S�Ț, W�"��E�b�<%�6D��V�+�F�#)Y�%AX�C�'*��z-��Ō@C�(���iS���oE��A����D�)���X����%�<MAoѩ{��Q�X+�bz" B�P�&C��v\�����KC�<Db���fi��+(|a�����w�aqK�m,�06.���+r�í)�kE�7��N�7ay��     