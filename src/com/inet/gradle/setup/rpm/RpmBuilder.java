/*
 * Copyright 2015 i-net software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.inet.gradle.setup.rpm;

import java.io.File;

import org.gradle.api.internal.file.FileResolver;

import com.inet.gradle.setup.AbstractBuilder;
import com.inet.gradle.setup.DesktopStarter;
import com.inet.gradle.setup.Service;
import com.inet.gradle.setup.SetupBuilder;

public class RpmBuilder extends AbstractBuilder<Rpm> {

	
	private RpmControlFileBuilder  controlBuilder;
    /**
     * Create a new instance
     * 
     * @param rpm the calling task
     * @param setup the shared settings
     * @param fileResolver the file Resolver
     */
    public RpmBuilder( Rpm rpm, SetupBuilder setup, FileResolver fileResolver ) {
        super( rpm, setup, fileResolver );
    }

    public void build() {
    	try {
            File filesPath = new File( buildDir, "/usr/share/" + setup.getBaseName() );
            task.copyTo( filesPath );
//            changeFilePermissionsTo644( filesPath );

            // 	create the package config files in the DEBIAN subfolder

            controlBuilder = new RpmControlFileBuilder( super.task, setup, new File( buildDir, "SPECS" ) );

            for( Service service : setup.getServices() ) {
//                setupService( service );
            }
            
            for( DesktopStarter starter : setup.getDesktopStarters() ) {
//                setupStarter( starter );
            }
            
            if( setup.getLicenseFile() != null ) {
//                setupEula();
            }

            controlBuilder.build();

//            documentBuilder = new DebDocumentFileBuilder( super.task, setup, new File( buildDir, "/usr/share/doc/" + setup.getBaseName() ) );
//            documentBuilder.build();

//            changeDirectoryPermissionsTo755( buildDir );

//            createDebianPackage();

//            checkDebianPackage();

        } catch( RuntimeException ex ) {
            throw ex;
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }

}
