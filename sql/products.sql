PGDMP         :                {            shopmedb    15.2    15.2 	    1           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            2           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            3           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            4           1262    16693    shopmedb    DATABASE     �   CREATE DATABASE shopmedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';
    DROP DATABASE shopmedb;
                postgres    false            �            1259    16701    products    TABLE     �   CREATE TABLE "shopping-cart".products (
    product_id integer NOT NULL,
    product_name character varying(256),
    product_description character varying(1024),
    product_image character varying(256),
    price double precision
);
 %   DROP TABLE "shopping-cart".products;
       shopping-cart         heap    postgres    false            �            1259    25714    products_product_id_seq    SEQUENCE     �   ALTER TABLE "shopping-cart".products ALTER COLUMN product_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "shopping-cart".products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            shopping-cart          postgres    false    216            -          0    16701    products 
   TABLE DATA                 shopping-cart          postgres    false    216   L	       5           0    0    products_product_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('"shopping-cart".products_product_id_seq', 9, true);
          shopping-cart          postgres    false    221            �           2606    16707    products products_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "shopping-cart".products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);
 I   ALTER TABLE ONLY "shopping-cart".products DROP CONSTRAINT products_pkey;
       shopping-cart            postgres    false    216            -   I  x�͑Mk�0�������k�6~lȨS��)�ձS���fk�ؤ��~��w��Kx��'�ܝ.=p���T$��i�@I���� �ZA�0�<h�qNI�λ�)�q��Hϐ'$,�pʚ�XO�Kg��g�~������jZ�.4���Dq
�<���᫄��
��j��i|˰ ��S͹���e��ݵJX0��?2݃L��d:'�qL�υM�32��~i-�#�l�<�XT$h��\��l�I�"�D��P�@!�F��&�$~��Wp��y���^�}�)bNw�MNb�w~/����5,b�2�R���p��>�.ca�W�"�5��]j�_=�G     