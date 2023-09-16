PGDMP     (    5                {            shopmedb    15.2    15.2 
    2           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            3           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            4           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            5           1262    16693    shopmedb    DATABASE     �   CREATE DATABASE shopmedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';
    DROP DATABASE shopmedb;
                postgres    false            �            1259    24651 
   cart_items    TABLE     �   CREATE TABLE "shopping-cart".cart_items (
    cart_id integer NOT NULL,
    username character varying,
    product_id integer,
    quantity integer
);
 '   DROP TABLE "shopping-cart".cart_items;
       shopping-cart         heap    postgres    false            �            1259    25705    cart_items_cart_id_seq    SEQUENCE     �   ALTER TABLE "shopping-cart".cart_items ALTER COLUMN cart_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "shopping-cart".cart_items_cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            shopping-cart          postgres    false    217            .          0    24651 
   cart_items 
   TABLE DATA                 shopping-cart          postgres    false    217   �
       6           0    0    cart_items_cart_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('"shopping-cart".cart_items_cart_id_seq', 109, true);
          shopping-cart          postgres    false    218            �           2606    24657    cart_items cart_items_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY "shopping-cart".cart_items
    ADD CONSTRAINT cart_items_pkey PRIMARY KEY (cart_id);
 M   ALTER TABLE ONLY "shopping-cart".cart_items DROP CONSTRAINT cart_items_pkey;
       shopping-cart            postgres    false    217            �           2606    24663    cart_items fk_cart_product    FK CONSTRAINT     �   ALTER TABLE ONLY "shopping-cart".cart_items
    ADD CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES "shopping-cart".products(product_id) NOT VALID;
 M   ALTER TABLE ONLY "shopping-cart".cart_items DROP CONSTRAINT fk_cart_product;
       shopping-cart          postgres    false    217            .   �   x���v
Q���WP*��/(��K�MN,*Q���%���
v��BiqjQ^bn��BAQ~Ji2D��41�$��RS�?�5(�����]!828��W!��'�B+hX�(�'&�ff����(�(�iZsy��!��1"�C�� �IV�     