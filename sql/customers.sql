PGDMP          8                {            shopmedb    15.2    15.2     0           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            1           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            2           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            3           1262    16693    shopmedb    DATABASE     �   CREATE DATABASE shopmedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';
    DROP DATABASE shopmedb;
                postgres    false            �            1259    25723 	   customers    TABLE     J  CREATE TABLE "shopping-cart".customers (
    username character varying(256) NOT NULL,
    first_name character varying(256) NOT NULL,
    last_name character varying(256) NOT NULL,
    email character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    user_type character varying(6),
    enabled boolean
);
 &   DROP TABLE "shopping-cart".customers;
       shopping-cart         heap    postgres    false            -          0    25723 	   customers 
   TABLE DATA                 shopping-cart          postgres    false    224   4       �           2606    25729    customers customers_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "shopping-cart".customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (username);
 K   ALTER TABLE ONLY "shopping-cart".customers DROP CONSTRAINT customers_pkey;
       shopping-cart            postgres    false    224            -   �   x�ŏM��0F�=�Ŧ �\��̂E%`���-2�)NűPo?hpv�����v���z�����|����41�J��T�b��*8�(ic�;�C����V�/�iy�ど�����g��i�H����
ʵ=#1c��epc�p�>��l]���|Ё�)*�E�����{�B��MA��VM8�*�(��P�3�F     