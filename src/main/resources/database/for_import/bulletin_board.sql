PGDMP      %                |            bulletin_board    16.4    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16524    bulletin_board    DATABASE     �   CREATE DATABASE bulletin_board WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
    DROP DATABASE bulletin_board;
                postgres    false            �            1259    16526    posts    TABLE     g  CREATE TABLE public.posts (
    id integer NOT NULL,
    title character varying(100),
    author character varying(50),
    password character varying(255),
    content text,
    views integer DEFAULT 0,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone,
    is_deleted boolean DEFAULT false
);
    DROP TABLE public.posts;
       public         heap    postgres    false            �            1259    16525    posts_id_seq    SEQUENCE     �   CREATE SEQUENCE public.posts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.posts_id_seq;
       public          postgres    false    216            �           0    0    posts_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.posts_id_seq OWNED BY public.posts.id;
          public          postgres    false    215            P           2604    16529    posts id    DEFAULT     d   ALTER TABLE ONLY public.posts ALTER COLUMN id SET DEFAULT nextval('public.posts_id_seq'::regclass);
 7   ALTER TABLE public.posts ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    16526    posts 
   TABLE DATA           p   COPY public.posts (id, title, author, password, content, views, created_at, updated_at, is_deleted) FROM stdin;
    public          postgres    false    216   z       �           0    0    posts_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.posts_id_seq', 47, true);
          public          postgres    false    215            U           2606    16535    posts posts_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.posts DROP CONSTRAINT posts_pkey;
       public            postgres    false    216            �   �  x��X=��&��S�9�U��	�����H�n$�:u���v�̡C;��;�!�gZ��*v9Q���{��x?�1TI�r��,�"A�������J�#FG�;F1��'�MO��W�?p�.�ZJce���,2�Rx���I���Vb/o��]���7�~��r�=-�q���M��]z�d����z�"��n��Ar��j8�7i�OJ�� ��| Zɫ���k�ܪ��7F�GhA��1N��c72ɓ�b�U����P��N������d��ŝqҼ�$'2����1
�o�N�0�~�����x��z�c��<�KoEy�I8�8��!������*���d ӂ�������BK�g��qVe�HH�(V,-h	!���#�,����.���̾�KpF��ͨ�n�S}�fG�u&�q!x���|�M������kVJe��;;f��G�FTB�+'�;�*�Xɧ)#��O�S ���#��a�$��T$t! P���iW�^�`U�N�j�VI��8ϖKf�T�Բ�#Ǵ�EDx. /g
)Rc�ͨB1����;V�ay_w�[."ؔ2��bї��>�z��n��6�f8-�(bXJ��n��z��Qa�"��ށa�E�Z�,"y�A`O��@�y���	����R�n!�V�q_��io��z���J���6�M��3y��6e�#<5M"�a2� q�Ĝ'b��G��$KB
������`��"���8�j,� �ϝ�D�w���qJH7��ӫ��YIHR�x�!��+�#E���O��?��÷?��_���O�O���S�?���;��;��;��;����۞9�"��^D��D4\��rF��4*����d�C�RMgN�t^K--�X;�1�5BZ*4�tVcm[�5��;_��>S��zp��S�Ѿt��m�p|��(L�v�;�fܹ�g\?G��	�����	�"�ʷ�)lvY����9:;9B�     