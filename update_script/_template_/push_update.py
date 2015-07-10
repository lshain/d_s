#coding=utf-8
import sys
import ConfigParser
import os
import glob
import re
import hashlib
import string

def list_dir(dir):
    all_dirs = []
    all_dirs.append(dir)
    for root, dirs, files in os.walk(dir, True):
        for name in dirs:
            cur_dir = os.path.join(root,name)
            if cur_dir.find(".svn") < 0:
                all_dirs.append(cur_dir)
    return all_dirs

def list_dir_files(path):
    files = []
    dirs = list_dir(path)
    for dir in dirs:
        for filename in glob.glob(dir + '/*.*'):
            if os.path.isfile(filename):
                files.append(filename)
    return files


def calc_file_md5(filename):
    m = hashlib.md5()
    a_file = open(filename, 'rb')    
    m.update(a_file.read())
    a_file.close()
    return m.hexdigest()


def initConfig():
    global dest_path
    global src_path
    
    config = ConfigParser.RawConfigParser()
    config.read("config.ini")
    
    dest_path = config.get("http_update","dest_path")
    src_path = config.get("http_update","src_path")
    
class version_des:
    def __init__(self, filename, version_id, md5, filesize):
        self.filename_ = filename
        self.version_id_ = version_id
        self.md5_ = md5
        self.filesize_ = filesize
    
    def inc_version_id(self, value):
        self.version_id_ = self.version_id_ + 1

    def set_md5(self, md5value):
        self.md5_ = md5value
    
    def set_filesize(self, filesize):
        self.filesize_ = filesize
        
    def get_filename(self):
        return self.filename_
        
    def get_version_id(self):
        return self.version_id_
        
    def get_md5(self):
        return self.md5_
        
    def get_filesize(self):
        return self.filesize_
        
def read_file_all(filename):
    content = []
    if os.path.exists(filename):
        file_obj = open(filename, 'r')
        content = file_obj.readlines()
        file_obj.close()
    return content
    
def parse_cur_version_list(version_file):
    version_des_lst = {}
    content = read_file_all(version_file)
    for line in content:
        split_lst = re.split(r'\|', line)
        filename = split_lst[0]
        version_id = string.atoi(split_lst[1])
        md5 = split_lst[2].replace('\n', '')
        filesize = string.atoi(split_lst[3].replace('\n', ''))
        vd = version_des(filename, version_id, md5, filesize)
        version_des_lst[filename] = vd
        
    return version_des_lst


def save_version_list(filename, version_lst):
    file_obj = open(filename, "w")
    for vd in version_lst.values():
        line = vd.get_filename() + '|' + str(vd.get_version_id()) + '|' + vd.get_md5() + '|' + str(vd.get_filesize()) + '\n'
        file_obj.write(line)
    file_obj.close()


def copy_update_files_do_dest(dest_path, src_path):
    cmd = 'cp -fr ' + src_path + '/* ' + dest_path + '/' 
    os.system(cmd)



initConfig()

modify_cnt = 0
new_cnt = 0

if not os.path.exists(dest_path):
    os.makedirs(dest_path)

cur_version_lst = parse_cur_version_list(dest_path + '/server_version.txt')
updata_file_lst = list_dir_files(src_path)
for filename in updata_file_lst:
    md5value = calc_file_md5(filename)
    filesize = os.path.getsize(filename)
    real_name = filename.replace(src_path + '/', '')
    if cur_version_lst.has_key(real_name):
        cur_vd = cur_version_lst[real_name]
        if cur_vd.get_md5() != md5value:    #文件有更改
            cur_vd.inc_version_id(1)
            cur_vd.set_md5(md5value)
            cur_vd.set_filesize(filesize)
            modify_cnt = modify_cnt + 1
    else:   #新增文件
        update_vd = version_des(real_name, 1, md5value, filesize)
        cur_version_lst[real_name] = update_vd
        new_cnt = new_cnt + 1

save_version_list(dest_path + '/server_version.txt', cur_version_lst)
copy_update_files_do_dest(dest_path, src_path)

print("update success...")
print("modified file cnt: " + str(modify_cnt))
print("new file cnt: " + str(new_cnt))










    
    


