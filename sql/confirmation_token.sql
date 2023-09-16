PGDMP     (    8                {            shopmedb    15.2    15.2 	    1           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            2           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            3           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            4           1262    16693    shopmedb    DATABASE     �   CREATE DATABASE shopmedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = icu LOCALE = 'en_US.UTF-8' ICU_LOCALE = 'en-US';
    DROP DATABASE shopmedb;
                postgres    false            �            1259    25716    confirmation_token    TABLE     4  CREATE TABLE "shopping-cart".confirmation_token (
    id bigint NOT NULL,
    token character varying(256) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    expires_at timestamp without time zone NOT NULL,
    confirmed_at timestamp without time zone,
    username character varying(256)
);
 /   DROP TABLE "shopping-cart".confirmation_token;
       shopping-cart         heap    postgres    false            �            1259    25715    token_id_seq    SEQUENCE     �   ALTER TABLE "shopping-cart".confirmation_token ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "shopping-cart".token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            shopping-cart          postgres    false    223            .          0    25716    confirmation_token 
   TABLE DATA                 shopping-cart          postgres    false    223   �	       5           0    0    token_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('"shopping-cart".token_id_seq', 41, true);
          shopping-cart          postgres    false    222            �           2606    25722    confirmation_token token_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY "shopping-cart".confirmation_token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY "shopping-cart".confirmation_token DROP CONSTRAINT token_pkey;
       shopping-cart            postgres    false    223            .   �  x��Z]�$�|�_������������d�b�{��w�C��a?f����;T���{��L�)*322�o�}x���������~|�����w�>���~\���q��5?������s�v�鮽>�o_��C�O����^�����=����u���?|�_�ϧ_���ۿ�}��Ӈ~��������oo.�?�~r�O�(�/Q�	ғ�٤ҝi���CoY�>�'c�qt��l^�V"mQ�є����_��?���������q(��XH9���\�a.�$	��Q�'Q)Zw(�3����|E������A�9�Y��)�S��������Ξ9.�%�!^���g��1G�C�͈�j�yk�&59U�m��B�a��$K$Jq���� �w5����O�W�&;<y֚M�� 3=��y�Ng�E�3o��Ζ�QI۵@Ѣ�
��>�ǧ#��L��b�T�
�C��.T;h��wT@�d!��mP��sE���%���t�9���_�|=9�RC��k&�Ć��Q�qPP����6���	���0�Ϩ����z��h!��7F��ӄ�p�lŴ�����9�;{�p�q��pE��_˴���":��4��K�H@�)�b��ۘZv�� ��䮨���bm����U{f��/_�9�bo͆�Rho�����.&E��4I�sB��,�.�������/LX~l��hL��Nś�ă�F�SS]����d �Y�B���o���3^4�r�,qӊe����[@v�SH:�q�ϴ)�`Z���K�a���qp�l�nTS,�1��#�XD^$xLc�1Y���[�ς�U��k\H�W�e�ÉPD� ���ԭN52��@2�?(�)�K�L�Z[{�;�;� @%�Ih�Mt��n�v�e�a2=�gS���v�dS�v�x*Z�E9�aa�尻b�B��d_���{�G�fzU_!1F�i��r�&� ���,�a�vb`�榝�!���ZSM��8���[/V�鮣��)i$kr�I��q*��b������l��J�*�1��F5%4k�Y�_���@EkZW8N�՝pk���U#o�	W�Re�k�[D��{�:U��h��h�}p�%��	�d/��g��F����F;��-;k�D���>Bt���?�*�Xk����艵#�z1��-5����~ꇥ�!m��OsE�Ű�:���իj�fg|E`p� �ުI����j�=$z�5���7A0�HWt�-�T#z����OK��������Bf.����mL���Y4�ۈ�A�
»l�ռ�GFf�Ka>�m��ڒͶ��}�G�m�9����m���0�^��o�<|^��@aL^����>��q0�F�ŗjZ��puݔ�FnT��_���H:�rb�������6������������FZ�9
a롢C��ގl���7�-��Ԡx�h@m�yc�E��CK�4�Y�E���G����p�R�Zɥ���i�.�&u{/�Zz99(�ºZ��Ұ�V�Tg�$�MNi�"��!,�������%9>Ӭ�ѩe.&!3��X�2C;[�ؔ�����Ng��6�t��+��l|.��١�EN|�D�^U��F͙a��q�R���s��E����4oe(.V��u�����E��{�t�bKөFRM���4��dQ|C_���	u��S���Z�ךW�E�<����(X���IťX�f�D�"�d�u�Tl4�BA\��
.hG�`�X����T������LH��f�����nD�����s0}'���!0Tд���pμ�X�p�Qѕ����Ӧ�[9���r�|L��D"�k+��ѵ�@&sUӃ�,=�f�����9�E�9 JN������Q�#��Z�(�`���PcM��i��pVq�t��U]�ɱ=M�Ǭs�U+X�VM�)�n�y����9֒|Ji'��^P��v]�?BW�~TvV�Y�t���q5�5C4�a;��R�p��|�@ۛ8���v��ӔrBkr<�RL7*-����d���ar
�T��F�B7_�[�!��)o���hC��ȋ�������h$L׉$�@ь����l�H+uc�o}��3A�Q��[�s�|EEo�k�hn��.��AӇ��s�$�ÓK��L�9��kC�����
��7pp��m�v�3�����|`Y��eg�`�M��e4hh�P�q2�Vl����5hvlr�����vc���������]�q���~M�~�/D`,     